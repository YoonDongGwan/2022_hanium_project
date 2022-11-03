package com.hanium.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.hanium.R

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var map : GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        var mapFragment : SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0
        val dormitory = LatLng(37.37440689307376,126.62942075337608)

        val makerOptions = MarkerOptions()
        makerOptions.position(dormitory)
        makerOptions.title("학교")
        makerOptions.snippet("기숙사")
        map.addMarker(makerOptions)



        val china = LatLng(37.37747074201524,126.63388235351424)

        val makerOptions2 = MarkerOptions()
        makerOptions2.position(china)
        makerOptions2.title("샹차이")
        makerOptions2.snippet("샹차이 가실분 (1/3)")
        map.addMarker(makerOptions2)

        val macdonald = LatLng(37.390105869587,126.64969234944282)

        val makerOptions3 = MarkerOptions()
        makerOptions3.position(macdonald)
        makerOptions3.title("맥도날드")
        makerOptions3.snippet("맥도날드~ (1/3)")
        map.addMarker(makerOptions3)


        map.moveCamera(CameraUpdateFactory.newLatLngZoom(dormitory,15f))
    }


}