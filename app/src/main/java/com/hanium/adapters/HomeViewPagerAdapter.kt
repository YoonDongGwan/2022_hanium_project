package com.hanium.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hanium.ResponseData
import com.hanium.R
import com.hanium.activities.ProductActivity

class HomeViewPagerAdapter(val context: Context, var arrayList: ArrayList<ResponseData>?) :
    RecyclerView.Adapter<HomeViewPagerAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val img: ImageView = itemView.findViewById(R.id.viewpager_img)
        val rankNum: TextView = itemView.findViewById(R.id.viewpager_rank_number)
        val name: TextView = itemView.findViewById(R.id.viewpager_food_name)
        val company: TextView = itemView.findViewById(R.id.viewpager_company)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewPagerAdapter.ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewpager_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: HomeViewPagerAdapter.ViewHolder, position: Int) {
        holder.company.text = arrayList!![position].company
        holder.name.text = arrayList!![position].name
        holder.rankNum.text = "${position+1}위"
        Glide.with(context).load(arrayList!![position].imgUrl).into(holder.img)
//        holder.itemView.setOnClickListener {
//            val intent = Intent(holder.itemView?.context, ProductActivity::class.java)
//            ContextCompat.startActivity(holder.itemView.context, intent, null)
//        }
    }

    override fun getItemCount(): Int = arrayList!!.size
}