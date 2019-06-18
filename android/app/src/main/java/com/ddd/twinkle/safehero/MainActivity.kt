package com.ddd.twinkle.safehero

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_bottom_navigation.*
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

fun Context.newIntentMainActivity() : Intent{
    val intent =Intent(this,MainActivity::class.java)
    return intent
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {

        bottom_navigation_view.setOnNavigationItemSelectedListener { menuItem ->
            onNavItemSelected(menuItem)
        }
    }
    private fun onNavItemSelected(menuItem : MenuItem) : Boolean{
        when(menuItem.itemId){
            R.id.navigation_home->{
                Timber.d("navigation_home")
                return true
            }
            R.id.navigation_dashboard->{
                Timber.d("navigation_dashboard")
                return true
            }
            R.id.navigation_notifications->{
                Timber.d("navigation_notifications")
                return true
            }
            R.id.navigation_info->{
                Timber.d("navigation_info")
                return true
            }
        }
        return false
    }
}
