package com.hanium.Chat

import android.opengl.Visibility
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
import com.hanium.ChatRoomResponse
import com.hanium.R
import com.hanium.RetrofitResponse
import com.hanium.RetrofitService
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class ChatData(val message: String = "", val nickname: String = "")

class ChatRoomActivity : AppCompatActivity(){
    lateinit var rv : RecyclerView
    lateinit var et: EditText
    lateinit var send_btn: ImageButton
    lateinit var adapter: ChatRecyclerAdapter
    private val array = ArrayList<ChatData>()
    private val db = Firebase.database
    private var myRef = db.getReference("chat")
    private var myNickname: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatroom)
        rv = findViewById(R.id.chat_recyclerview)
        et = findViewById(R.id.chat_message)
        send_btn = findViewById(R.id.send_btn)
        val layoutManager = LinearLayoutManager(applicationContext)
//        layoutManager.stackFromEnd = true
        rv.layoutManager = layoutManager

        val preferences = getSharedPreferences("UserInfo", 0)
        myNickname = preferences.getString("username", null)

        val intent = intent
        val storeNameTv: TextView = findViewById(R.id.chatroom_store_name)
        val storeContentTextView: TextView = findViewById(R.id.chatroom_store_content)
        val storeImage: ImageView = findViewById(R.id.chatroom_img)
        val stateTv: TextView = findViewById(R.id.chatroom_state)
        val dateTv : TextView = findViewById(R.id.chatroom_date)

        val storeName = intent.getStringExtra("storeName")
        if (storeName != null){
            Glide.with(this).load(intent.getStringExtra("imgUrl")).into(storeImage)
            storeNameTv.text = storeName
            storeContentTextView.text = intent.getStringExtra("content")

            myRef = myRef.child(storeName!!)
            myRef.addChildEventListener(childEventListener)
            et.setOnClickListener(onClickListener)
            send_btn.setOnClickListener(onClickListener)
            adapter = ChatRecyclerAdapter(array, myNickname!!)
            rv.adapter = adapter
        }
        else{
            val retrofit = Retrofit.Builder().baseUrl("http://52.78.209.45:3000")
                .addConverterFactory(GsonConverterFactory.create()).build()
            val service = retrofit.create(RetrofitService::class.java)

            service.getChatId(myNickname!!).enqueue(object : Callback<ChatRoomResponse> {
                override fun onResponse(call: Call<ChatRoomResponse>, response: Response<ChatRoomResponse>) {
                    if (response.isSuccessful){
                        var result: ChatRoomResponse? = response.body()
                        val store: String = result!!.store
                        val location: String = result.location
                        val date = result.date
                        val state = result.state
                        val chatId = result.chatId

                        storeImage.visibility = View.GONE
                        storeNameTv.text = store
                        stateTv.visibility = View.VISIBLE
                        stateTv.text = state
                        dateTv.visibility = View.VISIBLE
                        dateTv.text = date
                        storeContentTextView.text = "배달지 : $location"

                        myRef = myRef.child(chatId.toString())
                        myRef.addChildEventListener(childEventListener)
                        et.setOnClickListener(onClickListener)
                        send_btn.setOnClickListener(onClickListener)
                        adapter = ChatRecyclerAdapter(array, myNickname!!)
                        rv.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<ChatRoomResponse>, t: Throwable) {
                    Log.d("state", "onFailure" + t.message.toString())
                }

            })

        }
    }
    private val childEventListener = object : ChildEventListener{
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            var data = snapshot.getValue<ChatData>()
            val item = ChatData(data!!.message, data.nickname)

            adapter.addChat(item)
            rv.scrollToPosition(adapter.itemCount - 1)
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            var data = snapshot.getValue<ChatData>()
            val item = ChatData(data!!.message, data.nickname)
            adapter.addChat(item)
            rv.scrollToPosition(adapter.itemCount - 1)
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
                val data = ChatData(message, myNickname!!)
                myRef.push().setValue(data)
                et.text.clear()
            }
        }

    }
}