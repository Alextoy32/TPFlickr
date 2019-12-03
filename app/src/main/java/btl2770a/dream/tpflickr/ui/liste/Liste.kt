package btl2770a.dream.tpflickr.ui.liste

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import btl2770a.dream.tpflickr.R

class Liste : Fragment() {

    companion object {
        fun newInstance() = Liste()
    }

    private lateinit var viewModel: ListeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(ListeViewModel::class.java)
        val layout = inflater.inflate(R.layout.liste_fragment, container, false)
        val recycler = layout.findViewById<RecyclerView>(R.id.rView)

        viewModel.photo.observe(this, Observer {
            recycler.adapter = MyAdaptater(it) {

            }

            recycler.layoutManager = GridLayoutManager(this.context, 2)
        })

        return layout
    }

}
