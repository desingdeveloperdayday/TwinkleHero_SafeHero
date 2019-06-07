package com.ddd.twinkle.safehero.emergency

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ddd.twinkle.safehero.R
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.internal.GoogleServices
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


private const val INTENT_MAP_LAT = "lat"
private const val INTENT_MAP_LNG = "lng"
private const val UPDATE_INTERVAL_MS = 1000L

class EmergencyActivity : AppCompatActivity(),OnMapReadyCallback,GoogleMap.OnMarkerDragListener,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {
    override fun onConnected(p0: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var marker: MarkerOptions
    lateinit var mGoogleApiClient : GoogleApiClient
    lateinit var mGoogleMap : GoogleMap
    lateinit var currentMarker : Marker
    lateinit var locationrequest : LocationRequest

    private var lat : Double = 0.0
    private var lng : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency)
        setuplocationReqest()
        setupWindow()
        setupMapFragment()
        setupGoogleApiClient()
    }

    private fun setupGoogleApiClient() {
        mGoogleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .build()
    }

    private fun setupMapFragment() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    private fun setupWindow() {
        window.setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    private fun setuplocationReqest() {
        locationrequest = LocationRequest()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(UPDATE_INTERVAL_MS)
    }

    override fun onMapReady(map: GoogleMap?) {
        val position = LatLng(lat,lng)

        val marketOptions = MarkerOptions()
        marketOptions.position(position).draggable(true)
        map?.addMarker(marketOptions)
        map?.moveCamera(CameraUpdateFactory.newLatLng(position))
        map?.animateCamera(CameraUpdateFactory.zoomTo(15f))
        map?.setOnMarkerDragListener(this)
    }

    override fun onResume() {
        super.onResume()
        if(mGoogleApiClient.isConnected){
        }
        }

    override fun onMarkerDragEnd(p0: Marker?) {
        Toast.makeText(applicationContext,"onMarkerDragEnd", Toast.LENGTH_SHORT).show()
    }

    override fun onMarkerDragStart(p0: Marker?) {
        Toast.makeText(applicationContext,"onMarkerDragStart", Toast.LENGTH_SHORT).show()
    }

    override fun onMarkerDrag(p0: Marker?) {
        Toast.makeText(applicationContext,"onMarkerDrag", Toast.LENGTH_SHORT).show()
    }
}
