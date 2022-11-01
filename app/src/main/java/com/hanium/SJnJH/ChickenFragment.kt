package com.hanium.SJnJH

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanium.R
import com.hanium.activities.ProductActivity
import com.hanium.databinding.ItemStoresBinding


class ChickenFragment : Fragment() {


    lateinit var recyclerView1 : RecyclerView
    val chickenStoreArray = mutableListOf<StoreInform>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        var rootView = inflater.inflate(R.layout.fragment_chicken, container, false)
        for (i in 1..100){
            val name = "교촌치킨$i"
            val minPrice = i*100
            val deliveryTip = i*30
            chickenStoreArray.add(StoreInform(context?.let { getDrawable(it,R.drawable.asd) }!!,name, minPrice,deliveryTip))
        }
        recyclerView1 = rootView.findViewById(R.id.recyclerView1!!)as RecyclerView
        recyclerView1.layoutManager = LinearLayoutManager(requireContext())
        recyclerView1.adapter = ChickenListAdapter(requireContext(),chickenStoreArray)

        return rootView
    }
}


class ChickenListAdapter(val context: Context, val chickenStoreArray: List<StoreInform>) : RecyclerView.Adapter<ChickenListAdapter.Holder>() {

    class Holder(val binding: ItemStoresBinding) : RecyclerView.ViewHolder(binding.root){
        fun setItem(store: StoreInform) {
            with(binding) {
                imageView.setImageDrawable(store.image)
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
    override fun getItemCount() = chickenStoreArray.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener(){
            val intent = Intent(holder.itemView?.context, StoreActivity::class.java)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
        holder.setItem(chickenStoreArray.get(position))
    }
}