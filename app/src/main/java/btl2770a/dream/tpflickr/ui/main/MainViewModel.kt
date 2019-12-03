package btl2770a.dream.tpflickr.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import btl2770a.dream.tpflickr.model.Photo
import btl2770a.dream.tpflickr.model.SearchResult
import btl2770a.dream.tpflickr.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    var photo = MutableLiveData<Photo>()

    lateinit var listPhoto: List<Photo>
    var idPhoto: Int = 0

    init {
        Repository().getPhotos(object: Callback<SearchResult> {
            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                println("Get all photos failed")
            }
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                val liste = response.body()?.photos?.photo
                if (liste != null && liste.isNotEmpty()){
                    photo.postValue(liste[0])
                    listPhoto = liste
                }
            }
        })
    }

    fun nextPhoto(){
        if (idPhoto < listPhoto.lastIndex){
            idPhoto += 1
        } else {
            idPhoto = 0
        }
        photo.postValue(listPhoto[idPhoto])
    }


}
