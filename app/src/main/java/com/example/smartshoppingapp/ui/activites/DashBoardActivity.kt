package com.example.smartshoppingapp.ui.activites

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.smartshoppingapp.R
import com.example.smartshoppingapp.dataRepository.RemoteDataRepo
import com.example.smartshoppingapp.databinding.ActivityDashBoardBinding
import com.example.smartshoppingapp.viewModels.RegistrationViewModel
import com.example.smartshoppingapp.viewModels.ViewModelProviderFactory
import com.google.android.gms.location.LocationCallback

class DashBoardActivity : AppCompatActivity() {
    var _binding: ActivityDashBoardBinding? = null
    val binding get() = _binding!!
    var locationByGps: Location? = null
    var locationByNetwork: Location? = null
    private var currentLocation: Location? = null
    lateinit var locationManager: LocationManager

    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback


    val requestcode = 1001
    lateinit var viewModel: RegistrationViewModel

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDashBoardBinding.inflate(layoutInflater)

        val repo = RemoteDataRepo(this)
        val viewModelProviderFactory = ViewModelProviderFactory(repo)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(RegistrationViewModel::class.java)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        binding.bttmNav.setupWithNavController(navHostFragment.navController)


        setContentView(binding.root)
    }

}