package btl2770a.dream.tpflickr.ui.full

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import btl2770a.dream.tpflickr.R
import com.bumptech.glide.Glide

class FullFragment : Fragment() {

    companion object {
        fun newInstance() = FullFragment()
    }

    private lateinit var viewModel: FullViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val url = FullFragmentArgs.fromBundle(arguments!!).url
        val layout = inflater.inflate(R.layout.full_fragment, container, false)
        val imageView = layout.findViewById<ImageView>(R.id.fullImage)

        Glide.with(layout).load(url).into(imageView)
        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FullViewModel::class.java)
    }

}
