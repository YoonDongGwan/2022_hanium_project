package com.hanium.Chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanium.R

class ChatRoomListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room_list)
        val rv: RecyclerView = findViewById(R.id.chat_list_recyclerview)
        val arrayList = arrayListOf("교촌치킨 (1기숙사)", "미스터피자 (1기숙사)", "맘스터치 (1기숙사)")
        rv.adapter = ChatListAdapter(arrayList)
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}
class ChatListAdapter(val arrayList: ArrayList<String>): RecyclerView.Adapter<ChatListAdapter.ViewHolder>(){
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textview: TextView = itemView.findViewById(R.id.chat_list_store_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_list_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textview.text = arrayList[position]
    }

    override fun getItemCount(): Int = arrayList.size
}