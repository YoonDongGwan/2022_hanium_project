package com.hanium.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanium.R
import com.hanium.adapters.HomeRecyclerViewAdapter

class ProductListActivity : AppCompatActivity() {
     lateinit var backBtn: ImageButton
     lateinit var myClassBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        val recyclerview: RecyclerView = findViewById(R.id.product_list_recyclerview)
        val arrayList: ArrayList<Int> = arrayListOf(1,2,3,4,5,6,7,8,9,10)
        backBtn = findViewById(R.id.product_list_back_btn)
        myClassBtn = findViewById(R.id.product_list_myclass_book_btn)
        backBtn.setOnClickListener(onClickListener)
        myClassBtn.setOnClickListener(onClickListener)

        recyclerview.adapter = HomeRecyclerViewAdapter(arrayList)
        recyclerview.layoutManager = GridLayoutManager(this, 2)
    }
    val onClickListener = View.OnClickListener { view ->
        when(view.id){
            R.id.product_list_back_btn -> finish()

            R.id.product_list_myclass_book_btn -> {
                val intent = Intent(this, ClassActivity::class.java)
                startActivity(intent)
            }
        }
    }
}