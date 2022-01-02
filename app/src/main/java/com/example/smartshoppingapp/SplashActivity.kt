package com.example.smartshoppingapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.smartshoppingapp.AppUtils.SharedHelper
import com.example.smartshoppingapp.databinding.ActivitySplashBinding
import com.example.smartshoppingapp.ui.activites.DashBoardActivity

class SplashActivity : AppCompatActivity() {
    var _binding: ActivitySplashBinding? = null
    val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startNextActivity()

    }

    private fun startNextActivity() {
        val token = SharedHelper.getKey(this, "Cache")
        Log.e("Token:==>", "startNextActivity: $token")
        if (token.isNullOrEmpty()) {
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