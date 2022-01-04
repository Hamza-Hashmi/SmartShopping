package com.example.smartshoppingapp.ui.activites

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.smartshoppingapp.AppUtils.isInternetConnected
import com.example.smartshoppingapp.AppUtils.showGpsDialog
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
    val requestcode = 1001

    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback


    lateinit var viewModel: RegistrationViewModel

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDashBoardBinding.inflate(layoutInflater)
        if (isLocationPermissionGranted()) {
            if (isInternetConnected(this)) {
                /*Toast.makeText(
                    this,
                    "Success: Permission is Granted and GPS is Enabled",
                    Toast.LENGTH_SHORT
                ).show()*/
            } else {
                showGpsDialog(this)
            }
        }

        val repo = RemoteDataRepo(this)
        val viewModelProviderFactory = ViewModelProviderFactory(repo)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory)[RegistrationViewModel::class.java]

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        binding.bttmNav.setupWithNavController(navHostFragment.navController)


        setContentView(binding.root)
    }

    private fun isLocationPermissionGranted(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                requestcode
            )
            false
        } else {
            true
        }


    }


}