package com.hanium.activities

//import android.R

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.hanium.Chat.ChatRoomActivity
import com.hanium.OfflineData
import com.hanium.R
import org.json.JSONObject


class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var map : GoogleMap
    lateinit var card_view : LinearLayout
    lateinit var storeImg : ImageView
    lateinit var makeOffBt : Button
    lateinit var currentMarker: Marker
    lateinit var storeInfo : TextView
    lateinit var gatherBt : Button
    lateinit var noV : TextView
    lateinit var storeTv: TextView
    lateinit var contentTv: TextView
    var offArr : ArrayList<OfflineData> = ArrayList()
    var imgUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.hanium.R.layout.activity_map)

        noV = findViewById(R.id.noV)
        makeOffBt = findViewById(R.id.makeOffBt)
        card_view = findViewById(com.hanium.R.id.card_view)
        storeImg = findViewById(com.hanium.R.id.storeImg)
        storeInfo = findViewById(R.id.storeInfo)
        gatherBt = findViewById(R.id.gatherBt)

        var mapFragment : SupportMapFragment = supportFragmentManager.findFragmentById(com.hanium.R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        gatherBt.setOnClickListener{
            val intent = Intent(this, ChatRoomActivity::class.java)
            intent.putExtra("storeName", storeTv.text.toString())
            intent.putExtra("content", contentTv.text.toString())
            intent.putExtra("imgUrl", imgUrl)
            startActivity(intent)
        }
        getData()


        makeOffBt.setOnClickListener(){
            showDialog()
        }






    }

    fun showDialog() {

        val layoutR = layoutInflater.inflate(R.layout.dialog, null)
        var builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setView(layoutR)
        builder.setTitle("오프라인 매칭")
        val asd : EditText = layoutR.findViewById(R.id.content)
        val contents = asd.text



        builder.setPositiveButton("확인", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                var url = currentMarker.tag
                var lng = currentMarker.position
                var title = currentMarker.title
                var snip = currentMarker.snippet
                currentMarker.remove()

                val tempMakerOptions = MarkerOptions()
                tempMakerOptions.position(lng)
                tempMakerOptions.title(title)
                tempMakerOptions.alpha(0.98f)
                tempMakerOptions.snippet(snip+"\n$contents")



                val bd = getResources().getDrawable(com.hanium.R.drawable.dddd) as BitmapDrawable
                val b = bd.bitmap
                val bitMapImage = Bitmap.createScaledBitmap(b, 10, 20, false)
                tempMakerOptions.icon(BitmapDescriptorFactory.fromBitmap(b))
                val tempMarker: Marker = map.addMarker(tempMakerOptions)
                tempMarker.tag = url


                card_view.visibility = View.GONE

            }

        })

        builder.setNegativeButton("취소", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {


            }

        })
        builder.setCancelable(false)
        builder.show()

    }

    fun makeMarker(){
        var i = 0
        while (i<offArr.size){
            var tempArr : OfflineData = offArr[i]
            var lat = tempArr.lat
            var lon = tempArr.lon
            var title = tempArr.title
            var snippet = tempArr.snippet
            var tag = tempArr.tag
            var alpha = tempArr.alpha
            var content = tempArr.content


            if(alpha == 0.98){
                val tempLan = LatLng(lat,lon)

                val makerOptions = MarkerOptions()
                makerOptions.position(tempLan)
                makerOptions.title(title)
                makerOptions.snippet(snippet+"\n$content")
                makerOptions.alpha(alpha.toFloat())

                val bd = getResources().getDrawable(com.hanium.R.drawable.dddd) as BitmapDrawable
                val b = bd.bitmap
                val bitMapImage = Bitmap.createScaledBitmap(b, 10, 20, false)
                makerOptions.icon(BitmapDescriptorFactory.fromBitmap(b))
                val marker: Marker = map.addMarker(makerOptions)
                marker.tag = tag

            }

            else{
                val tempLn = LatLng(lat,lon)

                val makerOptions = MarkerOptions()
                makerOptions.position(tempLn)
                makerOptions.title(title)
                makerOptions.snippet(snippet)
                val marker: Marker = map.addMarker(makerOptions)
                marker.tag = tag


            }


            i++
        }
    }



    override fun onMapReady(p0: GoogleMap) {


        map = p0
        val dormitory = LatLng(37.37440689307376,126.62942075337608)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(dormitory,15f))



        map!!.setOnMarkerClickListener(object : GoogleMap.OnMarkerClickListener {
            override fun onMarkerClick(marker: Marker): Boolean {
                card_view.visibility = View.VISIBLE

                if(marker.alpha == 0.98f || marker.title.equals("샹차이")) {
                    makeOffBt.visibility = View.GONE
                    gatherBt.visibility = View.VISIBLE
                    noV.visibility = View.VISIBLE
                }
                else {
                    makeOffBt.visibility = View.VISIBLE
                    gatherBt.visibility = View.GONE
                    noV.visibility = View.INVISIBLE
                }



                storeTv = findViewById<TextView>(com.hanium.R.id.storeTv)
                contentTv = findViewById<TextView>(com.hanium.R.id.contentTv)


                // 마커 클릭시 카드뷰 보여줌
                var temp = marker.tag
                imgUrl = marker.tag.toString()
                storeTv.text = marker.title
                contentTv.text = marker.snippet

                Glide.with(this@MapActivity)
                    .load(temp)
                    .into(storeImg)

                currentMarker = marker


                return false
            }
        })


        map!!.setOnMapClickListener(object : GoogleMap.OnMapClickListener {
            override fun onMapClick(latLng: LatLng) {
                card_view.visibility = View.GONE
            }
        })




    }



    fun getData(){
        var url = "http://52.78.209.45:3000/store/offline"
        val requestQueue = Volley.newRequestQueue(this)

        val request: StringRequest = object : StringRequest(
            Request.Method.GET, url,request,fail ) {

            override fun getParams(): MutableMap<String, String> {
                val params : MutableMap<String,String> = HashMap()


                return params
            }
        }

        requestQueue.add(request)
    }

    var request = object  : Response.Listener<String> {
        override fun onResponse(response: String) {
            var jsonObject =  JSONObject(response)
            var temp = jsonObject.getJSONArray("data")
            var size = temp.length()

            var i = 0
            while (i<size) {
                var data = temp.getJSONObject(i)
                var lat = data.getDouble("lat")
                var lon = data.getDouble("lon")
                var title = data.getString("title")
                var snippet = data.getString("snippet")
                var tag = data.getString("tag")
                var alpha = data.getDouble("alpha")
                var content = data.getString("content")

                var tempArr = OfflineData(lat, lon, title, snippet, tag, alpha, content)
                offArr.add(tempArr)


                i++
            }

            makeMarker()




            }


        }
    }


    var fail = object  : Response.ErrorListener {
        override fun onErrorResponse(error: VolleyError?) {
            Log.d("aabb","서버 연결 실패 : $error")
        }
    }





