package com.ddd.twinkle.safehero.emergency

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.ddd.twinkle.safehero.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

const val LOCATION_PERMISSION_REQUEST_CODE = 1

class EmergencyActivity : AppCompatActivity(),OnMapReadyCallback,GoogleMap.OnMarkerClickListener{
    override fun onMarkerClick(p0: Marker?): Boolean {
        return false
    }

    lateinit var mMap : GoogleMap
    lateinit var currentLocation : Location

    lateinit var fucsedLocationClient: FusedLocationProviderClient

    lateinit var locationrequest : LocationRequest

    private var lat : Double = 0.0
    private var lng : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency)
        setupMapFragment()
        setupWindow()
        setupfucsedLocationClient()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled=true
        mMap.setOnMarkerClickListener(this)
        setupMap()
        /* mMap.addMarker(MarkerOptions().position(currentPos).title("현재 위치"))
         mMap.moveCamera(CameraUpdateFactory.newLatLng(currentPos))*/
    }

    //위치 정보 허가
    private fun setupMap() {

        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        mMap.isMyLocationEnabled=true
        fucsedLocationClient.lastLocation.addOnSuccessListener(this) {location->
            if(location !=null){
                currentLocation= location
                val currentLating = LatLng(location.latitude,(location.longitude)*-1)
                placeMakrerOnMap(currentLating)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLating,12f))
            }
        }
    }

    private fun setupfucsedLocationClient() {
        fucsedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    /*
    private fun setupGoogleApiClient() {
        mGoogleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConne^ctionFailedListener(this)
            .build()
    }*/

    private fun placeMakrerOnMap(location: LatLng){
        val markerOptions = MarkerOptions().position(location)
        mMap.addMarker(markerOptions)
    }

    private fun setupMapFragment() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    private fun setupWindow() {
        window.setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
/*
    private fun setuplocationReqest() {
        locationrequest = LocationRequest()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(UPDATE_INTERVAL_MS)
    }*/




}
