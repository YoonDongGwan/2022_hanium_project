package com.hanium.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanium.*
import com.hanium.SJnJH.MatchingReadyActivity
import com.hanium.SJnJH.MenuData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DeliveryInformationActivity : AppCompatActivity() {
    lateinit var matchDownBtn: ImageButton
    lateinit var matchUpBtn: ImageButton
    lateinit var matchNum: TextView
    var num: Int = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_information)
        val spinner: Spinner = findViewById(R.id.information_spinner)
        val recyclerview: RecyclerView = findViewById(R.id.del_inf_recyclerview)
        matchDownBtn = findViewById(R.id.matching_down_btn)
        matchUpBtn = findViewById(R.id.matching_up_btn)
        matchNum = findViewById(R.id.matching_number)
        val information_match_btn :Button = findViewById(R.id.information_match_btn)
        val storeName: TextView = findViewById(R.id.del_inf_store_name)
        val priceSum: TextView = findViewById(R.id.del_inf_price_sum)
        val retrofit = Retrofit.Builder().baseUrl("http://52.78.209.45:3000")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(RetrofitService::class.java)
        val intent = intent
        val selectedFoods: ArrayList<MenuData> = intent.getParcelableArrayListExtra("selectedFoods")!!

        storeName.text = intent.getStringExtra("storeName")
        val preferences: SharedPreferences = getSharedPreferences("UserInfo", 0)
        val editor = preferences.edit()
        val uid = preferences.getInt("uid", 0)


        val deliveryTip = intent.getIntExtra("deliveryTip",0)

        service.getDeliveryLocation().enqueue(object : Callback<LocationResponse> {
            override fun onResponse(call: Call<LocationResponse>, response: Response<LocationResponse>) {
                if (response.isSuccessful){
                    var result: LocationResponse? = response.body()
                    val arrayList = result?.data
                    val arrayAdapter = ArrayAdapter<String>(this@DeliveryInformationActivity, R.layout.support_simple_spinner_dropdown_item, arrayList!!)
                    spinner.adapter = arrayAdapter

                    information_match_btn.setOnClickListener{
                        service.postPreMatching(PreMatchingModel(intent.getIntExtra("priceSum", 0),Integer.parseInt(matchNum.text.toString()),arrayList[spinner.selectedItemId.toInt()],uid,deliveryTip)).enqueue(object : Callback<PreMatchingResult> {
                            override fun onResponse(call: Call<PreMatchingResult>, response: Response<PreMatchingResult>) {
                                val result = response.body()
                                val intent = Intent(this@DeliveryInformationActivity, MatchingReadyActivity::class.java)
                                intent.putExtra("totalPrice",intent.getIntExtra("priceSum", 0))
                                intent.putExtra("matchNum",Integer.parseInt(matchNum.text.toString()))
                                intent.putExtra("deliveryPlace",arrayList[spinner.selectedItemId.toInt()])
                                intent.putExtra("deliveryTip",deliveryTip)
                                editor.putString("username", result?.name)
                                editor.commit()

                                startActivity(intent)
                            }
                            override fun onFailure(call: Call<PreMatchingResult>, t: Throwable) {
                                Log.d("state", "onFailure" + t.message.toString())
                            }
                        })

                    }


                }
            }

            override fun onFailure(call: Call<LocationResponse>, t: Throwable) {
                Log.d("state", "onFailure" + t.message.toString())
            }

        })

        recyclerview.adapter = DelInfRecyclerViewAdapter(this@DeliveryInformationActivity, selectedFoods)
        recyclerview.layoutManager = LinearLayoutManager(this@DeliveryInformationActivity)
        priceSum.text = "${intent.getIntExtra("priceSum", 0)} 원"

        matchDownBtn.setOnClickListener{
            if (num > 2){
                num -= 1
            }
            matchNum.text = num.toString()
        }
        matchUpBtn.setOnClickListener{
            if (num < 4){
                num += 1
            }
            matchNum.text = num.toString()
        }


    }
}
class DelInfRecyclerViewAdapter(val context: Context, val arrayList: ArrayList<MenuData>): RecyclerView.Adapter<DelInfRecyclerViewAdapter.ViewHolder>(){
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val menuName: TextView = itemView.findViewById(R.id.del_inf_menu_name)
        val price: TextView = itemView.findViewById(R.id.del_inf_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.del_inf_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.menuName.text = arrayList[position].menu
        holder.price.text = "${arrayList[position].price} 원"
    }

    override fun getItemCount(): Int = arrayList.size
}