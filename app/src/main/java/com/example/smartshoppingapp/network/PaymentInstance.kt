package com.example.smartshoppingapp.network

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PaymentInstance {
    val BASE_Pay_URL = "https://salty-shelf-49885.herokuapp.com/api/"

    fun getPayApi(context: Context): DataApi {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_Pay_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(DataApi::class.java)

    }


}