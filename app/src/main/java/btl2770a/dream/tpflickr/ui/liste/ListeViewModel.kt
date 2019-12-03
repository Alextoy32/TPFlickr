package btl2770a.dream.tpflickr.ui.liste

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import btl2770a.dream.tpflickr.model.Photo
import btl2770a.dream.tpflickr.model.SearchResult
import btl2770a.dream.tpflickr.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListeViewModel : ViewModel() {
    var photo = MutableLiveData<List<Photo>>()

    lateinit var listPhoto: List<Photo>

    init {
        Repository().getPhotos(object: Callback<SearchResult> {
            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                println("Get all photos failed")
            }
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                val liste = response.body()?.photos?.photo
                if (liste != null && liste.isNotEmpty()){
                    photo.postValue(liste)
                    listPhoto = liste
                }
            }
        })
    }
}
