package com.hanium.SJnJH

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.hanium.R
import com.hanium.ResponseData
import com.hanium.RetrofitResponse
import com.hanium.RetrofitService
import com.hanium.activities.DeliveryInformationActivity
import com.hanium.adapters.HomeViewPagerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class StoreActivity : AppCompatActivity() {
    lateinit var a : ImageButton
    lateinit var b : ImageButton
    lateinit var c : ImageButton
    lateinit var startBt: Button
    lateinit var callBt : RelativeLayout
    lateinit var heartBt : RelativeLayout
    lateinit var shareBt : RelativeLayout
    lateinit var rv : RecyclerView
    var arr: ArrayList<ItemData> = ArrayList()
    var allChecked = 0
    var menuArr : ArrayList<MenuData> = ArrayList()

    var menu = ""
    var finalPrice = 0
    var priceString = ""
    var zzim = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        a = findViewById(R.id.a)
        b = findViewById(R.id.b)
        c = findViewById(R.id.c)
        rv = findViewById(R.id.rv)
        startBt = findViewById(R.id.startBt)
        callBt = findViewById(R.id.callBt)
        heartBt = findViewById(R.id.heartBt)
        shareBt = findViewById(R.id.shareBt)

        a.setOnClickListener(){
            var intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0537207900")
            if(intent.resolveActivity(packageManager) != null){
                startActivity(intent)
            }
        }

        callBt.setOnClickListener(){
            var intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0328157900")
            if(intent.resolveActivity(packageManager) != null){
                startActivity(intent)
            }
        }

        b.setOnClickListener(){
            if (!zzim) {
                b.setImageResource(R.drawable.ic_baseline_favorite_24)
                zzim = true
            }
            else{
                b.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                zzim = false
            }


        }

        heartBt.setOnClickListener(){
            if (!zzim) {
                b.setImageResource(R.drawable.ic_baseline_favorite_24)
                zzim = true
            }
            else{
                b.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                zzim = false
            }


        }

        c.setOnClickListener(){
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.type = "text/plain"
            startActivity(Intent.createChooser(sendIntent, "Share"))

        }

        shareBt.setOnClickListener(){
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.type = "text/plain"
            startActivity(Intent.createChooser(sendIntent, "Share"))

        }


        val intent = intent
        val company = intent.getStringExtra("company")

        val retrofit = Retrofit.Builder().baseUrl("http://52.78.209.45:3000")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(RetrofitService::class.java)
        if (company != null) {
            service.getStoreMenu(company).enqueue(object : Callback<RetrofitResponse> {
                override fun onResponse(
                    call: Call<RetrofitResponse>,
                    response: Response<RetrofitResponse>
                ) {
                    if (response.isSuccessful) {
                        var result: RetrofitResponse? = response.body()
                        val arrayList = result?.data
                        rv.adapter = MyRvAdapter(this@StoreActivity, arrayList)

                    }
                }

                override fun onFailure(call: Call<RetrofitResponse>, t: Throwable) {
                    Log.d("state", "onFailure" + t.message.toString())
                }

            })
        }

        rv.layoutManager = LinearLayoutManager(this)

        val dividerItemDecoration =
            DividerItemDecoration(rv.context, LinearLayoutManager(this).orientation)

        rv.addItemDecoration(dividerItemDecoration)

//        adapter.notifyDataSetChanged()


        startBt.setOnClickListener(){
            val intent = Intent(this, DeliveryInformationActivity::class.java)
//            val nextIntent = Intent(this, FindingPeopleActivity::class.java)
            startActivity(intent)
        }


    }


    inner class MyRvAdapter(val context: Context, val arr: ArrayList<ResponseData>?) :
        RecyclerView.Adapter<MyRvAdapter.Holder>() {

        inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
            var chk : CheckBox = itemView.findViewById(R.id.chk)
            var iv: ImageView = itemView.findViewById(R.id.iv)
            val tv1: TextView = itemView.findViewById(R.id.tv1)
            val tv2: TextView = itemView.findViewById(R.id.tv2)

        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(context).inflate(R.layout.rec_item, parent, false)

            return Holder(view)
        }

        override fun getItemCount(): Int {
            return arr!!.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            Glide.with(context).load(arr!![position].imgUrl).into(holder.iv)
            holder.tv1.text = arr[position].name
            holder.tv2.text = "${arr[position].price} 원"


            holder.chk.setOnClickListener(){

                if (!holder.chk.isChecked){
                    allChecked--

                    if (allChecked == 0){
                        startBt.visibility = View.GONE
                    }

                    val menu = arr[position].name
                    val price = arr[position].price

//                    for (i in 0 until )



                    finalPrice -= price

                    startBt.setText(finalPrice.toString()+"원 매칭하기")

                }


                if(holder.chk.isChecked){
                    allChecked++

                    if (allChecked != 0){
                        startBt.visibility = View.VISIBLE
                    }

                    val menu = arr[position].name
                    val price= arr[position].price

                    menuArr.add(MenuData(menu, price))
                    finalPrice += price

                    startBt.setText(finalPrice.toString()+"원 매칭하기")
                }



//                var menu = arr.get(position).menu
//                var priceString = arr.get(position).price
//
//                var price = priceString.split("원")
//                var num = price[0]
//                Log.d("ssdd","{$menu},가격: $num")
//                startBt.setText(num+"원 매칭하기")
            }


            holder.itemView.setOnClickListener {
                var i = 0
                var asddd = ""
                while (i<menuArr.size){
                    asddd += menuArr[i].menu
                    i++
                }
                var asdd = menuArr
                Log.d("ddbb","msg:{$asddd} ")
            }

        }

    }


}