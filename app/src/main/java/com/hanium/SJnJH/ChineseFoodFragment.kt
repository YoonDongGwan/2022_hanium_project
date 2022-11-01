package com.hanium.SJnJH

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getDrawable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanium.R
import com.hanium.databinding.ItemStoresBinding


class ChineseFoodFragment : Fragment() {


    lateinit var recyclerView : RecyclerView
    val chineseStoreArray = mutableListOf<StoreInform>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        var rootView = inflater.inflate(R.layout.fragment_chinese_food, container, false)
        for (i in 1..100){
            val name = "짜장면$i"
            val minPrice = i*100
            val deliveryTip = i*30
            chineseStoreArray.add(StoreInform(context?.let { getDrawable(it,R.drawable.asd) },name, minPrice,deliveryTip))
        }
        recyclerView = rootView.findViewById(R.id.chineseFoodRecyclerView!!)as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ChineseFoodAdapter(requireContext(),chineseStoreArray)

        return rootView
    }
}


class ChineseFoodAdapter(val context: Context, val chineseStoreArray: List<StoreInform>) : RecyclerView.Adapter<ChineseFoodAdapter.Holder>() {

    class Holder(val binding: ItemStoresBinding) : RecyclerView.ViewHolder(binding.root){
        fun setItem(store: StoreInform) {
            with(binding) {
                storeName.text = store.name
                minPrice.text = "${store.minPrice}원"
                deliveryTip.text = "${store.deliveryTip}원"
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemStoresBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }
    override fun getItemCount(): Int {
        return chineseStoreArray.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setItem(chineseStoreArray.get(position))

    }
}