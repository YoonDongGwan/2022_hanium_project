package com.hanium.Chat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.hanium.R
import com.hanium.ResponseData
import com.hanium.RetrofitResponse
import com.hanium.RetrofitService
import com.hanium.adapters.HomeViewPagerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChatRoomListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room_list)
        val rv: RecyclerView = findViewById(R.id.chat_list_recyclerview)
        val backBtn: ImageButton = findViewById(R.id.chat_list_back_btn)
        val preferences = getSharedPreferences("UserInfo", 0)
        val name = preferences.getString("username", null)
        val retrofit = Retrofit.Builder().baseUrl("http://52.78.209.45:3000")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(RetrofitService::class.java)
        if (name != null) {
            service.getChatList(name).enqueue(object : Callback<RetrofitResponse> {
                override fun onResponse(call: Call<RetrofitResponse>, response: Response<RetrofitResponse>) {
                    if (response.isSuccessful){
                        var result: RetrofitResponse? = response.body()
                        val arrayList = result?.data
                        rv.adapter = ChatListAdapter(arrayList)
                        rv.layoutManager = LinearLayoutManager(this@ChatRoomListActivity, LinearLayoutManager.VERTICAL, false)
                    }
                }

                override fun onFailure(call: Call<RetrofitResponse>, t: Throwable) {
                    Log.d("state", "onFailure" + t.message.toString())
                }

            })
        }

        backBtn.setOnClickListener{
            finish()
        }
    }
}
class ChatListAdapter(val arrayList: ArrayList<ResponseData>?): RecyclerView.Adapter<ChatListAdapter.ViewHolder>(){
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textview: TextView = itemView.findViewById(R.id.chat_list_store_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_list_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textview.text = "${arrayList!![position].company} (${arrayList[position].location})"
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, ChatRoomActivity::class.java)
            intent.putExtra("id", arrayList[position].id)
            intent.putExtra("company", arrayList[position].company)
            intent.putExtra("location", arrayList[position].location)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount(): Int = arrayList!!.size
}