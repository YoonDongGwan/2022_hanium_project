package com.hanium.sjnjh

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanium.R
import com.hanium.databinding.ItemStoresBinding


class WesternFoodFragment : Fragment() {

    lateinit var recyclerView : RecyclerView
    val westernFoodStoreArray = mutableListOf<StoreInform>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        var rootView = inflater.inflate(R.layout.fragment_western_food, container, false)
        for (i in 1..100){
            val name = "파스타$i"
            val minPrice = i*100
            val deliveryTip = i*30
            westernFoodStoreArray.add(StoreInform(context?.let {
                AppCompatResources.getDrawable(
                    it,
                    R.drawable.asd
                )
            }!!,name, minPrice,deliveryTip))
        }
        recyclerView = rootView.findViewById(R.id.westernFoodRecyclerView!!)as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = WesternFoodListAdapter(requireContext(),westernFoodStoreArray)

        return rootView
    }
}


class WesternFoodListAdapter(val context: Context, val westernFoodStoreArray: List<StoreInform>) : RecyclerView.Adapter<WesternFoodListAdapter.Holder>() {

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
    override fun getItemCount() = westernFoodStoreArray.size

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.setItem(westernFoodStoreArray.get(position))

}