package com.example.smartshoppingapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.smartshoppingapp.AppUtils.SharedHelper
import com.example.smartshoppingapp.databinding.ActivitySplashBinding
import com.example.smartshoppingapp.ui.activites.DashBoardActivity
import com.example.smartshoppingapp.viewModels.RegistrationViewModel
import com.google.firebase.database.*

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class SplashActivity : AppCompatActivity() {
    var _binding: ActivitySplashBinding? = null
    val binding get() = _binding!!
    lateinit var viewModel: RegistrationViewModel
    lateinit var alertDialog: AlertDialog.Builder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        val database = Firebase.database
        val myRef = database.getReference("payment")
        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val isPayment = snapshot.child("paymentDone").getValue(String::class.java)
                if (!isPayment.equals("true")) {
                    Toast.makeText(this@SplashActivity, "Error!", Toast.LENGTH_SHORT).show()
                } else {
                    startNextActivity()
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

        setContentView(binding.root)

    }

    private fun startNextActivity() {
        val state = SharedHelper.getKey(this, "userID")
        if (state?.isEmpty()!!) {
            Handler(Looper.getMainLooper()).postDelayed({
                val i = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(i)
                finish()
            }, (2 * 1000).toLong())
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                val i = Intent(this@SplashActivity, DashBoardActivity::class.java)
                startActivity(i)
                finish()
            }, (2 * 1000).toLong()) // wait for 5 seconds

        }
    }

/*

    private fun requestMultiplePermissions() {

        permReqLuncher.launch(REQUIRE_MULTIPLE_PERMISSIONS)

    }

    private fun allPermissionsGranted() = REQUIRE_MULTIPLE_PERMISSIONS.all {

        ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }

    val permReqLuncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->

            permissions.entries.forEach {
                Log.e("TAG", "${it.key} = ${it.value}")
            }
            val granted = permissions.entries.all {
                it.value == true
            }

            if (granted) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permisison Required", Toast.LENGTH_SHORT).show()
            }
        }

*/

}