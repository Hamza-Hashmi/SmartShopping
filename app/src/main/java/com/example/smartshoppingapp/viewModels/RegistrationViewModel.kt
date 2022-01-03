package com.example.smartshoppingapp.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartshoppingapp.dataRepository.RemoteDataRepo
import com.example.smartshoppingapp.model.*
import kotlinx.coroutines.launch
import retrofit2.Response

class RegistrationViewModel(val repo:RemoteDataRepo):ViewModel() {

    /*simple Login*/

    val _loginResponse: MutableLiveData<Response<LoginResponse>> = MutableLiveData()

    /* signup Response */
    val _signUpResposne: MutableLiveData<Response<SignupResponse>> = MutableLiveData()

    val _shopList: MutableLiveData<Response<ShopListResponse>> = MutableLiveData()

    val _shopProductList:MutableLiveData<Response<ShopProductResponse>> = MutableLiveData()
    /* methods*/

    fun signUp(userRegistration: SignupModel) = viewModelScope.launch {


        val response: Response<SignupResponse> = repo.signUp(userRegistration)

        _signUpResposne.postValue(response)
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        val response = repo.login(email, password)
        _loginResponse.postValue(response)
    }

    fun getShopList() = viewModelScope.launch {
        val response = repo.getShopList()
        _shopList.postValue(response)
        Log.e("DATA", "getShopList: ${response.body()?.data}" )
    }


    fun getShopProductList(id:Int) = viewModelScope.launch {
         val response = repo.getProductsList(id)
        _shopProductList.postValue(response)

    }

/*
    */
/* handlers *//*

    private fun handleSignUpResponse(response: Response<SignUpResponse>):Resources<SignUpResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resources.Success(resultResponse)
            }
        }
        return Resources.Faliour(response.message())
    }
*/

   /* private fun handleLoginResponse(response: Response<LoginResponse>):Resources<LoginResponse>{

        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resources.Success(resultResponse)
            }
        }
        return Resources.Faliour(response.message())
    }*/
}