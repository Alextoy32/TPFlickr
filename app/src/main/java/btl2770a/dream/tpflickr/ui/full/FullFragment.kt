package btl2770a.dream.tpflickr.ui.full

import android.content.ContentValues.TAG
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar

import btl2770a.dream.tpflickr.R
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.full_fragment.*

class FullFragment : Fragment() {

    companion object {
        fun newInstance() = FullFragment()
    }

    private lateinit var viewModel: FullViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val db = FirebaseFirestore.getInstance()
        val url = FullFragmentArgs.fromBundle(arguments!!).url
        val title = FullFragmentArgs.fromBundle(arguments!!).title
        val layout = inflater.inflate(R.layout.full_fragment, container, false)
        val imageView = layout.findViewById<ImageView>(R.id.fullImage)
        val ratingBar = layout.findViewById<RatingBar>(R.id.ratingBar)

        Glide.with(layout).load(url).into(imageView)

        ratingBar.setOnRatingBarChangeListener{ ratingBar, fl, b ->
            viewModel.rate = fl

            val db = FirebaseFirestore.getInstance()
            val user = FirebaseAuth.getInstance().currentUser

            val image = hashMapOf(
                "url" to url,
                "rate" to fl
            )

            db.collection("users").document(user!!.email!!).collection("images").document(title)
                .set(image)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }


        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FullViewModel::class.java)
    }

}
