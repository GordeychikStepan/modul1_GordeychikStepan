package com.bignerdranch.android.cinemaapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bignerdranch.android.cinemaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //checkPermission()

        // переключение м/у экранами навигации
        binding.navView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    switchFragment(NavigationHomeFragment())
                    true
                }
                R.id.navigation_collection -> {
                    switchFragment(NavigationCollectionsFragment())
                    true
                }
                R.id.navigation_set -> {
                    switchFragment(NavigationSetFragment())
                    true
                }
                R.id.navigation_profile -> {
                    switchFragment(NavigationProfileScreenFragment())
                    true
                }
                else -> false
            }
        }

        // установка селектора для цвета иконок и текста при нажатии
        binding.navView.itemIconTintList = resources.getColorStateList(R.drawable.bottom_nav_item_color)
        binding.navView.itemTextColor = resources.getColorStateList(R.drawable.bottom_nav_item_color)

        switchFragment(NavigationHomeFragment())
    }

    /*private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Разрешение не предоставлено
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1
            )
        } else {
            // Разрешение уже предоставлено
        }
    }*/

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }

}