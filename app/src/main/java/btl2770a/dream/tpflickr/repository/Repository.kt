package btl2770a.dream.tpflickr.repository

import btl2770a.dream.tpflickr.model.SearchResult
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Repository {

    fun getPhotos(callback: Callback<SearchResult>) {
        val url = "https://www.flickr.com"
        val retrofit =
            Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        val service = retrofit.create(FlickrAPI::class.java)
        service.getInterestingPhotos(
            "flickr.interestingness.getList",
            "edbdb339b7ecd4f1cca79a472f76ebad",
            "20"
        ).enqueue(callback)
    }


}