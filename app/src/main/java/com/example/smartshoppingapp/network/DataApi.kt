package com.example.smartshoppingapp.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface DataApi {

/*
    @FormUrlEncoded
    @POST("/api/login")
    suspend fun login(@Field("email") email:String, @Field("password") password:String): Response<LoginResponse>
*/
    @POST("register")
    suspend fun signUp(@Body userRegistration: UserRegistration): Response<SignUpResponse>


}