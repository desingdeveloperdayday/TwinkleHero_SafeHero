package com.ddd.twinkle.safehero.monitoring

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.ddd.twinkle.safehero.R
import com.ddd.twinkle.safehero.emergency.ConvertToString
import com.ddd.twinkle.safehero.emergency.LOCATION_PERMISSION_REQUEST_CODE
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.bottom_navigation_view
import kotlinx.android.synthetic.main.activity_monitoring.*
import timber.log.Timber

fun Context.newIntentMonitoringAcrtivity() : Intent{
    val intent = Intent(this,MonitoringActivity::class.java)
    return intent
}

class MonitoringActivity : AppCompatActivity(),OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    lateinit var mMap : GoogleMap
    lateinit var currentLocation : Location
    lateinit var fucsedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitoring)
        setupMapFragment()
        setupBottomNavigationView()
        setupfucsedLocationClient()
    }

    private fun setupMapFragment() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    //위치 정보 허가
    private fun requestPermissionsForLocation() {

        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }else{
            Toast.makeText(this,"위치 정보 권한이 필요합니다", Toast.LENGTH_SHORT).show()
        }
        mMap.isMyLocationEnabled=true

        fucsedLocationClient.lastLocation.addOnSuccessListener(this) {location->
            location?.let {
                currentLocation= location
                val currentLating = LatLng(location.latitude,(location.longitude))
                placeMakrerOnMap(currentLating)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLating,12f))
                Timber.d("latitude ${currentLating.latitude} longitude ${currentLating.longitude}")
            }
        }
    }
    private fun setupfucsedLocationClient() {
        fucsedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    private fun placeMakrerOnMap(location: LatLng){
        val markerOptions = MarkerOptions().position(location)

        val titleString = ConvertToString.getAddress(this,location)
        markerOptions.title(titleString)
        Timber.d("titleString ${titleString} ")
        mMap.addMarker(markerOptions)

        titleString?.let {
            edit_starter.setText(titleString)
        }?:run {
            return@run
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled=true
        mMap.setOnMarkerClickListener(this)
        requestPermissionsForLocation()
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        return false
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
