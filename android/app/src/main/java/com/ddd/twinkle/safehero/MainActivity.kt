package com.ddd.twinkle.safehero

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ddd.twinkle.safehero.calling.CustomDialog
import com.ddd.twinkle.safehero.calling.newIntentCallingActivity
import com.ddd.twinkle.safehero.monitoring.newIntentMonitoringAcrtivity
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

fun Context.newIntentMainActivity() : Intent{
    val intent =Intent(this,MainActivity::class.java)
    return intent
}

class MainActivity : AppCompatActivity() {

    lateinit var customDialog: CustomDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigationView()
        setupButton()
        setupCustomButton()
    }

    private fun setupCustomButton() {
        customDialog = CustomDialog(this)
    }

    private fun setupButton() {
        layout_call_me.setOnClickListener {
            startActivity(newIntentCallingActivity())
        }
        layout_send_message.setOnClickListener {
            customDialog.show()
        }
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
                startActivity(newIntentMonitoringAcrtivity())
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
