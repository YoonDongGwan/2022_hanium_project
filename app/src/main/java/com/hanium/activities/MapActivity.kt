package com.hanium.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.hanium.R

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var map : GoogleMap
    lateinit var card_view : LinearLayout
    lateinit var storeImg : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        card_view = findViewById(R.id.card_view)
        storeImg = findViewById(R.id.storeImg)

        var mapFragment : SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0
        val dormitory = LatLng(37.37440689307376,126.62942075337608)

//        val makerOptions = MarkerOptions()
//        makerOptions.position(dormitory)
//        makerOptions.title("학교")
//        makerOptions.snippet("기숙사")
//        map.addMarker(makerOptions)



        val china = LatLng(37.37747074201524,126.63388235351424)

        val makerOptions2 = MarkerOptions()
        makerOptions2.position(china)
        makerOptions2.title("샹차이")
        makerOptions2.snippet("샹차이 가실분")
        val marker2: Marker = map.addMarker(makerOptions2)
        marker2.tag =
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZURWXtA7dNpyFqr8OrOfiF9iIu41oeXLa2A&usqp=CAU"

        val macdonald = LatLng(37.390105869587,126.64969234944282)

        val makerOptions3 = MarkerOptions()
        makerOptions3.position(macdonald)
        makerOptions3.title("맥도날드")
        makerOptions3.snippet("맥도날드 상하이버거 고")
        val marker3: Marker = map.addMarker(makerOptions3)
        marker3.tag =
            "data:image/jpeg;base64,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"


        val makerOptions4 = MarkerOptions()
        val pork = LatLng( 37.37439380433282,126.633897309035)
        makerOptions4.position(pork)
        makerOptions4.title("최고당돈까스")
        makerOptions4.snippet("치돈이랑 돈까스 드실분")
        val marker4: Marker = map.addMarker(makerOptions4)
        marker4.tag =
            "data:image/jpeg;base64,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"


        map.moveCamera(CameraUpdateFactory.newLatLngZoom(dormitory,15f))





        map!!.setOnMarkerClickListener(object : GoogleMap.OnMarkerClickListener {
            override fun onMarkerClick(marker: Marker): Boolean {
                card_view.visibility = View.VISIBLE
                var storeTv = findViewById<TextView>(R.id.storeTv)
                var contentTv = findViewById<TextView>(R.id.contentTv)
//                var numTv = findViewById<TextView>(R.id.numTv)

                var temp = marker.tag
                storeTv.text = marker.title
                contentTv.text = marker.snippet

                Glide.with(this@MapActivity)
                    .load(temp)
                    .into(storeImg)



                return false
            }
        })


        map!!.setOnMapClickListener(object : GoogleMap.OnMapClickListener {
            override fun onMapClick(latLng: LatLng) {
                card_view.visibility = View.GONE
            }
        })



    }


}