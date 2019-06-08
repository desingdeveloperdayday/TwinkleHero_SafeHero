package com.ddd.twinkle.safehero.emergency

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.ddd.twinkle.safehero.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import timber.log.Timber

const val LOCATION_PERMISSION_REQUEST_CODE = 1

class EmergencyActivity : AppCompatActivity(),OnMapReadyCallback,GoogleMap.OnMarkerClickListener{

    lateinit var mMap : GoogleMap
    lateinit var currentLocation : Location
    lateinit var fucsedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency)
        setupMapFragment()
        setupfucsedLocationClient()
    }

    private fun setupMapFragment() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled=true
        mMap.setOnMarkerClickListener(this)
        setupMap()
    }

    //위치 정보 허가
    private fun setupMap() {

        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
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
    }


    override fun onMarkerClick(p0: Marker?): Boolean {
        return false
    }





}
