package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface UserListService {
    @Headers("Content-Type:application/json")
    @GET("/api/users?page=2")
    fun GetUser() : Call<ResponseUserData>
}