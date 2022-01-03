package com.example.smartshoppingapp.dataRepository

import android.content.Context
import com.example.smartshoppingapp.model.*
import com.example.smartshoppingapp.network.RetrofitInstance
import retrofit2.Response

class RemoteDataRepo(val context: Context) {
    suspend fun signUp(userRegistration: SignupModel): Response<SignupResponse> =
        RetrofitInstance.getapi(context).signUp(userRegistration)

    suspend fun login(email: String, password: String): Response<LoginResponse> =
        RetrofitInstance.getapi(context).login(email, password)

    suspend fun getShopList(): Response<ShopListResponse> =
        RetrofitInstance.getapi(context).getShopList()

    suspend fun getProductsList(id:Int):Response<ShopProductResponse> = RetrofitInstance.getapi(context).getProductsList(id)


    suspend fun getPayment(): Response<PaymentResponse> =
        PaymentInstance.getPayApi(context).getPayment()

    suspend fun addCart(cartModel: CartModel) : Response<CartResponse> = RetrofitInstance.getapi(context).addCart(cartModel)
}


