package com.example.vinilos.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.vinilos.R
import com.example.vinilos.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navView: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        Log.d("act", navController.toString())
        setSupportActionBar(findViewById(R.id.my_toolbar))
        setupActionBarWithNavController(navController)

        /*navView = binding.navView
        navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.collectorFragment,
                R.id.AlbumsFragment,
                R.id.artistasFragment,
                R.id.coleccionistasFragment,
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)*/
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}