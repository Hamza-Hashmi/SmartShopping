package com.example.smartshoppingapp.dataRepository

import android.content.Context
import com.example.smartshoppingapp.model.LoginResponse
import com.example.smartshoppingapp.model.SignupModel
import com.example.smartshoppingapp.model.SignupResponse
import com.example.smartshoppingapp.network.RetrofitInstance
import retrofit2.Response

class RemoteDataRepo(val context: Context) {
    suspend fun signUp(userRegistration: SignupModel): Response<SignupResponse> = RetrofitInstance.getapi(context).signUp(userRegistration)

    suspend fun login(email:String,password:String):Response<LoginResponse> = RetrofitInstance.getapi(context).login(email,password)
}