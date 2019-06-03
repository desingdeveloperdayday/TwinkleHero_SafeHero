package com.ddd.twinkle.safehero.emergency

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


private const val INTENT_MAP_LAT = "lat"
private const val INTENT_MAP_LNG = "lng"

class EmergencyActivity : AppCompatActivity(),OnMapReadyCallback,GoogleMap.OnMarkerDragListener {

    lateinit var marker: MarkerOptions

    private var lat : Double = 0.0
    private var lng : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.ddd.twinkle.safehero.R.layout.activity_emergency)

        val fragmentManager = fragmentManager
        val mapFragment = supportFragmentManager
            .findFragmentById(com.ddd.twinkle.safehero.R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
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
