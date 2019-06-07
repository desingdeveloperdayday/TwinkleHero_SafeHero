package com.ddd.twinkle.safehero.emergency

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ddd.twinkle.safehero.R
import com.google.android.gms.common.api.GoogleApiClient
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



class EmergencyActivity : AppCompatActivity(),OnMapReadyCallback,GoogleMap.OnMarkerClickListener{
    override fun onMarkerClick(p0: Marker?): Boolean {
        return false
    }

    lateinit var googleMap : GoogleMap
    lateinit var currentMarker : Marker

    lateinit var mGoogleApiClient : GoogleApiClient
    lateinit var fucsedLocationClient: FusedLocationProviderClient

    lateinit var locationrequest : LocationRequest

    private var lat : Double = 0.0
    private var lng : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency)
        //setuplocationReqest()
        setupWindow()
        setupfucsedLocationClient()
        setupMapFragment()
       // setupGoogleApiClient()
    }

    private fun setupfucsedLocationClient() {
        fucsedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }
/*
    private fun setupGoogleApiClient() {
        mGoogleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .build()
    }*/

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

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val currentPos = LatLng(lat,lng)
        googleMap.addMarker(MarkerOptions().position(currentPos).title("현재 위치"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentPos))
    }


}
