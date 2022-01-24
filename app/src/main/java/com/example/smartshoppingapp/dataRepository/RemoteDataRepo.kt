package com.example.smartshoppingapp.dataRepository

import android.content.Context
import com.example.smartshoppingapp.model.*
import com.example.smartshoppingapp.network.PaymentInstance
import com.example.smartshoppingapp.network.RetrofitInstance
import retrofit2.Response

class RemoteDataRepo(val context: Context) {
    suspend fun signUp(userRegistration: SignupModel): Response<SignupResponse> =
        RetrofitInstance.getapi(context).signUp(userRegistration)

    suspend fun login(email: String, password: String): Response<LoginResponse> =
        RetrofitInstance.getapi(context).login(email, password)

    suspend fun getShopList(): Response<ShopListResponse> =
        RetrofitInstance.getapi(context).getShopList()

    suspend fun getProductsList(id: Int): Response<ShopProductResponse> =
        RetrofitInstance.getapi(context).getProductsList(id)


    suspend fun getPayment(): Response<PaymentResponse> =
        PaymentInstance.getPayApi(context).getPayment()

    suspend fun addCart(cartModel: CartModel): Response<CartResponse> =
        RetrofitInstance.getapi(context).addCart(cartModel)

    suspend fun getCartList(user_id: Int): Response<CartListResponse> =
        RetrofitInstance.getapi(context).getCart(user_id)

    suspend fun placeOrder(User_id: Int): Response<OrderResponse> =
        RetrofitInstance.getapi(context).placeOrder(User_id)

    suspend fun getNotification(user_id:Int):Response<NotificationResponse> =
          RetrofitInstance.getapi(context).notification(user_id)
}


