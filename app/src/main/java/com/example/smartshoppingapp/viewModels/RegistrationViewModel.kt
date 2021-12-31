package com.example.smartshoppingapp.viewModels

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartshoppingapp.dataRepository.RemoteDataRepo
import com.example.smartshoppingapp.model.LoginResponse
import com.example.smartshoppingapp.model.SignupModel
import com.example.smartshoppingapp.model.SignupResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class RegistrationViewModel(val repo:RemoteDataRepo):ViewModel() {

    /*simple Login*/

    val _loginResponse: MutableLiveData<Response<LoginResponse>> = MutableLiveData()

    /* signup Response */
    val _signUpResposne: MutableLiveData<Response<SignupResponse>> = MutableLiveData()



    /* methods*/

    fun signUp(userRegistration: SignupModel) = viewModelScope.launch {
       // _signUpResposne.postValue(Resources.Loading())

          val response: Response<SignupResponse> = repo.signUp(userRegistration)

        _signUpResposne.postValue(response)
    }
    fun login(email:String,password:String) = viewModelScope.launch {
        val response = repo.login(email,password)
        _loginResponse.postValue(response)
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