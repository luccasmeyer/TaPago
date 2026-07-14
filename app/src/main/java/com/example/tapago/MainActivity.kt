package com.example.tapago

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.tapago.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateOption()

    }

    private fun navigateOption(){
        val navHost = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHost.navController

        binding.includeBottonMenu.bottomNavigationBnv.setupWithNavController(navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}