package com.example.smartshoppingapp.ui.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.smartshoppingapp.R
import com.example.smartshoppingapp.databinding.ActivityDashBoardBinding

class DashBoardActivity : AppCompatActivity() {
    var _binding: ActivityDashBoardBinding? = null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDashBoardBinding.inflate(layoutInflater)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        binding.bttmNav.setupWithNavController(navHostFragment.navController)


        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}