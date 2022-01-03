package com.example.smartshoppingapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.smartshoppingapp.AppUtils.SharedHelper
import com.example.smartshoppingapp.dataRepository.RemoteDataRepo
import com.example.smartshoppingapp.databinding.ActivitySplashBinding
import com.example.smartshoppingapp.ui.activites.DashBoardActivity
import com.example.smartshoppingapp.viewModels.RegistrationViewModel
import com.example.smartshoppingapp.viewModels.ViewModelProviderFactory

class SplashActivity : AppCompatActivity() {
    var _binding: ActivitySplashBinding? = null
    val binding get() = _binding!!
    lateinit var viewModel: RegistrationViewModel
    lateinit var alertDialog: AlertDialog.Builder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)

        val repo = RemoteDataRepo(this)
        val viewModelProviderFactory = ViewModelProviderFactory(repo)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(
            RegistrationViewModel::class.java
        )

        viewModel.getPayment()
        viewModel._payment.observe(this, {
            Toast.makeText(this, "${it.body()?.error}", Toast.LENGTH_SHORT).show()

            if (it.body()?.error!!.equals("false")) {
                alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("Error!")
                alertDialog.setMessage(it.body()?.message)
                alertDialog.show()
            } else {
                startNextActivity()
            }

        })

        setContentView(binding.root)

    }

    private fun startNextActivity() {
        val state = SharedHelper.getKey(this, "state")
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