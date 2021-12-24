package com.example.smartshoppingapp.ui.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.smartshoppingapp.R
import com.example.smartshoppingapp.databinding.ActivityDashBoardBinding

class DashBoardActivity : AppCompatActivity() {
    lateinit var binding:ActivityDashBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bttmNav,
            navHostFragment.navController)

    }
}