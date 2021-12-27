package com.example.proyekakhirmonitoringporang

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.proyekakhirmonitoringporang.databinding.ActivityBottomNavBinding

class MainActivity : AppCompatActivity() {

    private var backPressedTime = 0L

    private lateinit var binding: ActivityBottomNavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_bottom_nav)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_monitoring,
                R.id.navigation_riwayat,
                R.id.navigation_catat,
                R.id.navigation_hasil,
                R.id.navigation_profil
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed()
        } else {
            Toast.makeText(applicationContext, "Tekan kembali untuk meninggalkan aplikasi", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()

    }
}