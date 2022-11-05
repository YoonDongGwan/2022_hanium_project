package com.hanium.Chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.*

import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue

import com.google.firebase.ktx.Firebase
import com.hanium.R

data class ChatData(val message: String = "", val nickname: String = "")

class ChatRoomActivity : AppCompatActivity(){
    lateinit var rv : RecyclerView
    lateinit var et: EditText
    lateinit var send_btn: ImageButton
    lateinit var adapter: ChatRecyclerAdapter
    private val array = ArrayList<ChatData>()
    private val db = Firebase.database
    private var myRef = db.getReference("chat")
    private val myNickname = "david"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatroom)
        rv = findViewById(R.id.chat_recyclerview)
        et = findViewById(R.id.chat_message)
        send_btn = findViewById(R.id.send_btn)
        val layoutManager = LinearLayoutManager(applicationContext)
        layoutManager.stackFromEnd = true
        rv.layoutManager = layoutManager



        val intent = intent
        val storeNameTv: TextView = findViewById(R.id.chatroom_store_name)
        val storeContentTextView: TextView = findViewById(R.id.chatroom_store_content)
        val storeImage: ImageView = findViewById(R.id.chatroom_img)

        val storeName = intent.getStringExtra("storeName")
        Glide.with(this).load(intent.getStringExtra("imgUrl")).into(storeImage)
        myRef = myRef.child(storeName!!)
        storeNameTv.text = storeName
        storeContentTextView.text = intent.getStringExtra("content")

        myRef.addChildEventListener(childEventListener)
        send_btn.setOnClickListener(onClickListener)
        adapter = ChatRecyclerAdapter(array, myNickname)
        rv.adapter = adapter


    }
    private val childEventListener = object : ChildEventListener{
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            var data = snapshot.getValue<ChatData>()
            val item = ChatData(data!!.message, data.nickname)
            adapter.addChat(item)
            rv.scrollToPosition(adapter.itemCount - 1)
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            TODO("Not yet implemented")
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {
            TODO("Not yet implemented")
        }

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            TODO("Not yet implemented")
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }
    }
    private val onClickListener = View.OnClickListener { view ->
        when (view.id){
            R.id.send_btn -> {
                val message = et.text.toString()
                val data = ChatData(message, myNickname)
                myRef.push().setValue(data)
                et.text.clear()
            }
        }

    }
}