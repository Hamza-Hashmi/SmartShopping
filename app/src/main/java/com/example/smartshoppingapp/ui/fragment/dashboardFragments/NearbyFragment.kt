package com.example.smartshoppingapp.ui.fragment.dashboardFragments

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.smartshoppingapp.Adapter.ShopsAdapter
import com.example.smartshoppingapp.R
import com.example.smartshoppingapp.viewModels.RegistrationViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory


class NearbyFragment : Fragment() {

    private lateinit var viewModel: RegistrationViewModel
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var shopsAdapter: ShopsAdapter

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*     viewModel = (activity as DashBoardActivity).viewModel

             val mapFragment = fragmentManager?.findFragmentById(R.id.map) as SupportMapFragment?

             mapFragment?.getMapAsync { googleMap ->
                 fusedLocationProviderClient =
                     LocationServices.getFusedLocationProviderClient(requireContext())
                 val task = fusedLocationProviderClient.lastLocation
                 viewModel._shopList.observe(viewLifecycleOwner, {
                     shopsAdapter =
                         ShopsAdapter(requireContext(), it.body()?.data as ArrayList<ShoplistData>)
                     for (i in 0 until shopsAdapter.itemCount) {
                         val data = it.body()!!
                         val lat = data.data[i].latitude
                         val long = data.data[i].longitude

                         Toast.makeText(requireContext(), "$lat,$long", Toast.LENGTH_SHORT).show()
                         googleMap.addMarker(
                             MarkerOptions()
                                 .position(LatLng(33.63590279711394, 73.07044803999847))
                                 .title(data.data[i].shop_name)
                                 .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.ic_shop))
                         )?.showInfoWindow()
                     }
                 })
                 task.addOnSuccessListener {
                     val marker = LatLng(it.latitude, it.longitude)
                     googleMap.addMarker(MarkerOptions().position(marker).title("Marker in Sydney"))
                     val location = CameraUpdateFactory.newLatLngZoom(
                         marker, 15f
                     )
                     googleMap.animateCamera(location)
                 }
                 viewModel._shopList.observe(viewLifecycleOwner, {
                     shopsAdapter =
                         ShopsAdapter(requireContext(), it.body()?.data as ArrayList<ShoplistData>)

                     for (i in 0 until shopsAdapter.itemCount) {
                         val data = it.body()!!
                         val lat = data.data[i].latitude
                         val long = data.data[i].longitude

                         Toast.makeText(requireContext(), "$lat,$long", Toast.LENGTH_SHORT).show()
                         googleMap.addMarker(
                             MarkerOptions()
                                 .position(LatLng(33.63590279711394, 73.07044803999847))
                                 .title(data.data[i].shop_name)
                                 .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.ic_shop))
                         )?.showInfoWindow()
                     }
                 })
             }*/
        return inflater.inflate(R.layout.fragment_nearby, container, false)
    }

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    /*fun CalculationByDistance(StartP: LatLng, EndP: LatLng): Double {
        val Radius = 6371 // radius of earth in Km
        val lat1 = StartP.latitude
        val lat2 = EndP.latitude
        val lon1 = StartP.longitude
        val lon2 = EndP.longitude
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = (Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + (Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2)))
        val c = 2 * Math.asin(Math.sqrt(a))
        val valueResult = Radius * c
        val km = valueResult / 1
        val newFormat = DecimalFormat("####")
        val kmInDec: Int = Integer.valueOf(newFormat.format(km))
        val meter = valueResult % 1000
        val meterInDec: Int = Integer.valueOf(newFormat.format(meter))
        Log.i(
            "Radius Value", "" + valueResult + "   KM  " + kmInDec
                    + " Meter   " + meterInDec
        )
        return Radius * c
    }*/
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap =
                Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }
