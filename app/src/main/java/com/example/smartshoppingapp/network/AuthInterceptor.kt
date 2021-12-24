package com.example.smartshoppingapp.network

import android.content.Context
import com.example.smartshoppingapp.AppUtils.Constants.Companion.authToken
import com.example.smartshoppingapp.AppUtils.SharedHelper
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        SharedHelper.getKey(context,authToken).let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(requestBuilder.build())
    }
}