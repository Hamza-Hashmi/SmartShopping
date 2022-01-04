package com.example.smartshoppingapp.network

import com.example.smartshoppingapp.model.*
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

    @GET("shop-products/{user_id}")
    suspend fun getProductsList(@Path("user_id") id: Int): Response<ShopProductResponse>


    @GET("userPayment")
    suspend fun getPayment(): Response<PaymentResponse>

    @POST("add-to-cart")
    suspend fun addCart(@Body cartModel: CartModel): Response<CartResponse>


    @FormUrlEncoded
    @POST("my-cart")
    suspend fun getCart(@Field("user_id") userId: Int): Response<CartListResponse>

    @FormUrlEncoded
    @POST("place-order")
    suspend fun placeOrder(@Field("user_id") User_id: Int): Response<OrderResponse>
}