package btl2770a.dream.tpflickr.repository

import btl2770a.dream.tpflickr.model.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrAPI {

    @GET("/services/rest?format=json&nojsoncallback=1")
    fun getInterestingPhotos(
        @Query("method") method: String,
        @Query("api_key") key: String,
        @Query("per_page") perpage: String
    ): Call<SearchResult>

}