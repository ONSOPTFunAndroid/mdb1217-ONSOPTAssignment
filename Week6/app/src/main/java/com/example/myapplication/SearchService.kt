package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchService {
    @Headers("Authorization : KakaoAK {REST_API_KEY}")
    @GET("/v2/search/web")
    fun getWebSearch(
        @Query("query") web: String
    ): Call<WebSearchData>
}