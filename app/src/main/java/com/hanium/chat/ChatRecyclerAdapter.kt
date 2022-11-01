package com.hanium.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hanium.R


class ChatRecyclerAdapter(val array: ArrayList<ChatData>, val myNickname: String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nickname: TextView = itemView.findViewById(R.id.myUserName)
        val message: TextView = itemView.findViewById(R.id.myChatContent)
    }
    inner class yourViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nickname: TextView = itemView.findViewById(R.id.yourUserName)
        val message: TextView = itemView.findViewById(R.id.yourChatContent)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            0 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.mychat, parent, false)
                myViewHolder(view)
            }
            1 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.yourchat, parent, false)
                yourViewHolder(view)
            }
            else -> {
                throw RuntimeException("ViewType Error")
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is myViewHolder) {
            holder.nickname.text = array[position].nickname
            holder.message.text = array[position].message
        }
        else if(holder is yourViewHolder){
            holder.nickname.text = array[position].nickname
            holder.message.text = array[position].message
        }
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(array[position].nickname == myNickname) 0 else 1
    }

    fun addChat(data: ChatData){
        array.add(data)
        notifyItemInserted(array.size-1)
    }

}