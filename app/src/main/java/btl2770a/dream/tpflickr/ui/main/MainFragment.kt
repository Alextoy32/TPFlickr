package btl2770a.dream.tpflickr.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import btl2770a.dream.tpflickr.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.main_fragment.view.*


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var imageView: ImageView
    private lateinit var titleView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val layout =  inflater.inflate(R.layout.main_fragment, container, false)

        imageView = layout.findViewById(R.id.imageView)
        titleView = layout.findViewById(R.id.titleImage)
        viewModel.photo.observe(this, Observer { photo ->
            val url = "https://farm" + photo.farm + ".staticflickr.com/" + photo.server + "/" + photo.id + "_" + photo.secret + ".jpg"
            Glide.with(activity!!).load(url).into(imageView);
            titleView.text = photo.title
            imageView.setOnClickListener{
                val action = MainFragmentDirections.actionMainFragmentToFullFragment(url,photo.title)
                findNavController().navigate(action)
            }
        })



        val buttonNext: Button = layout.findViewById(R.id.nextBtn)
        buttonNext.setOnClickListener {
            viewModel.nextPhoto()
        }
        val buttonAll: Button = layout.findViewById(R.id.allBtn)
        buttonAll.setOnClickListener {
            Navigation.findNavController(layout).navigate(R.id.versListeFragment);
        }

        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
