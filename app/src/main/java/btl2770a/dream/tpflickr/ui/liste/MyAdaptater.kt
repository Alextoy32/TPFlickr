package btl2770a.dream.tpflickr.ui.liste

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import btl2770a.dream.tpflickr.R
import btl2770a.dream.tpflickr.model.Photo
import btl2770a.dream.tpflickr.ui.main.MainFragmentDirections
import com.bumptech.glide.Glide


@Suppress("CAST_NEVER_SUCCEEDS")
class MyAdaptater(private val photos: List<Photo>, val callback: (Int) -> Unit) :
    RecyclerView.Adapter<MyAdaptater.MyViewHolder>() {

    var currentUser = -1;

    class MyViewHolder(val v: LinearLayout) :
        RecyclerView.ViewHolder(v)

    // appelé quand le ViewHolder doit être créé (probablement parce que l'item devient vi sible)
    // on crée (inflate) le layout "user" et on le place dans le ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            MyViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.image, parent, false)

        return MyViewHolder(mView as LinearLayout)
    }

    // appelé quand le recycerview a besoin de connaître la taille de la liste qu'il doit afficher
    override fun getItemCount(): Int = photos.size

    // appelé quand on doit peupler le ViewHolder avec le contenu de l'élément numéro "position"
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val imageView = holder.v.findViewById<ImageView>(R.id.imageV)

        val url =
            "https://farm" + photos[position].farm + ".staticflickr.com/" + photos[position].server + "/" + photos[position].id + "_" + photos[position].secret + ".jpg"

        Glide.with(holder.v).load(url).into(imageView)

        imageView.setOnClickListener{
            val action = ListeDirections.actionListeToFullFragment(url)
            Navigation.findNavController(holder.v).navigate(action);
        }
    }
}