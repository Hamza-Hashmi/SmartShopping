package com.example.smartshoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.smartshoppingapp.dataRepository.RemoteDataRepo
import com.example.smartshoppingapp.databinding.ActivityMainBinding
import com.example.smartshoppingapp.viewModels.RegistrationViewModel
import com.example.smartshoppingapp.viewModels.ViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: RegistrationViewModel

  lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val repo = RemoteDataRepo(this@MainActivity)
        val viewModelProviderFactory = ViewModelProviderFactory(repo)
        viewModel = ViewModelProvider(this@MainActivity, viewModelProviderFactory).get(RegistrationViewModel::class.java)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment

    }
}