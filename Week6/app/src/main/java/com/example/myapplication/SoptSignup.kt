package com.example.myapplication

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SoptSignup {
    @Headers("Content-Type:application/json")
    @POST("users/signup")
    fun postSignup(
        @Body body : RequestSignupData
    ) : Call<ResponseSignupData>
}