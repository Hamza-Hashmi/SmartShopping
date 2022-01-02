package com.example.smartshoppingapp.ui.fragment.dashboardFragments

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.smartshoppingapp.R
import com.example.smartshoppingapp.ui.activites.DashBoardActivity
import com.example.smartshoppingapp.viewModels.RegistrationViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class NearbyFragment : Fragment() {

    lateinit var viewModel: RegistrationViewModel
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    val requestcode = 1001

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        viewModel = (activity as DashBoardActivity).viewModel
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        if (isLocationPermissionGranted()) {
            val task = fusedLocationProviderClient.lastLocation
            /* for (pos: Int in 0..it.body()?.data?.size!!) {
                 val body = it.body()!!
                 val lat = body.data[pos].latitude
                 val long = body.data[pos].longitude

                 val shopLocationMarker = LatLng(lat.toDouble(), long.toDouble())
                 googleMap.addMarker(
                     MarkerOptions().position(shopLocationMarker)
                         .title("Shop name:${body.data[pos].shop_name}")
                 )

             }*/
            task.addOnSuccessListener {
                val marker = LatLng(it.latitude, it.longitude)
                googleMap.addMarker(MarkerOptions().position(marker).title("Marker in Sydney"))
//                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker,15f))

                val location = CameraUpdateFactory.newLatLngZoom(
                    marker, 15f
                )
                googleMap.animateCamera(location)
            }
        }


    }

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_nearby, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun isLocationPermissionGranted(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
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