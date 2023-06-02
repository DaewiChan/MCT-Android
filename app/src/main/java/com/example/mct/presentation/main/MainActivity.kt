package com.example.mct.presentation.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mct.R
import com.example.mct.presentation.account.profile.ProfileFragment
import com.example.mct.presentation.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    var navigationView: BottomNavigationView? = null

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    fun initUI(){
        navigationView = findViewById(R.id.bottomNavigation)

        loadFragment(HomeFragment())
        catchEvent()
    }

    fun catchEvent(){
        navigationView?.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            val id = item.itemId
            when (id) {

                R.id.nav_home -> {
                    // startActivity(SearchActivity.newIntent(this))
                    val homeFragment = HomeFragment()
                    loadFragment(homeFragment)
                    return@OnItemSelectedListener true
                }

                R.id.nav_profile -> {
                    val profileFragment = ProfileFragment()
                    loadFragment(profileFragment)
                }
            }
            true
        })
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}