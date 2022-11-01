package com.hanium.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.hanium.R
import com.hanium.adapters.HomeRecyclerViewAdapter
import com.hanium.adapters.HomeViewPagerAdapter
import com.hanium.sjnjh.*

class MainActivity : AppCompatActivity() {
    lateinit var viewpager: ViewPager2
    lateinit var recyclerview: RecyclerView
    lateinit var myPageBtn: ImageButton
    var btns = arrayOfNulls<Button>(8)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewpager = findViewById(R.id.home_viewpager)
        recyclerview = findViewById(R.id.home_recyclerview)
        myPageBtn = findViewById(R.id.myPageBtn)

        for(i in 0..7){
            btns[i] = findViewById(resources.getIdentifier("home_list_btn${i+1}","id",packageName))
            btns[i]?.setOnClickListener(onClickListener)
        }

        val arrayList: ArrayList<Int> = arrayListOf(1,2,3,4,5,6,7,8,9,10)

        recyclerview.adapter = HomeRecyclerViewAdapter(arrayList)
        recyclerview.layoutManager = GridLayoutManager(this, 2)

        viewpager.adapter = HomeViewPagerAdapter(arrayList)
        viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        myPageBtn.setOnClickListener{
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }

    }

    private val onClickListener = View.OnClickListener { view ->
        when(view.id){
            R.id.home_list_btn8 -> {
                val intent = Intent(this, StoreActivity::class.java)
                startActivity(intent)
            }
            else -> {
                val intent = Intent(this, ProductListActivity::class.java)
                startActivity(intent)
            }
        }
    }
}