package com.example.smartshoppingapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.smartshoppingapp.dataRepository.RemoteDataRepo

class ViewModelProviderFactory (val repo: RemoteDataRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)){
        return RegistrationViewModel(repo) as T

        //}
        //else return DashBoardViewModel(booksRepository) as T

    }
}