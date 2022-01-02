package com.example.smartshoppingapp.network

import com.example.smartshoppingapp.model.LoginResponse
import com.example.smartshoppingapp.model.ShopListResponse
import com.example.smartshoppingapp.model.SignupModel
import com.example.smartshoppingapp.model.SignupResponse
import retrofit2.Response
import retrofit2.http.*

interface DataApi {


    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>


    @POST("register")
    suspend fun signUp(@Body registration: SignupModel): Response<SignupResponse>

    @GET("shop-list")
    suspend fun getShopList(): Response<ShopListResponse>
}