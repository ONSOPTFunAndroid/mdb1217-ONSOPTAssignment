package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SearchServiceImpl {
    private const val BASE_URL = "https://dapi.kakao.com"
    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service : SearchService = retrofit.create(SearchService::class.java)
}