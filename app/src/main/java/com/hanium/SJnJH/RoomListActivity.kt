package com.hanium.SJnJH

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.hanium.databinding.ActivityRoomListBinding
import com.hanium.databinding.ItemRecyclerviewBinding

class RoomListActivity : AppCompatActivity() {
    val binding by lazy{ ActivityRoomListBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val list=loadData()

        val adapter = ListAdapter(list)
        binding.viewpager.adapter=adapter

        val titles=listOf("치킨","피자","햄버거","중식","양식","기타")

        TabLayoutMediator(binding.tabLayout,binding.viewpager){tab,position->
            tab.text=titles.get(position)
        }.attach()
    }

    fun loadData(): List<ListForm>{
        val pageList= mutableListOf<ListForm>()
        pageList.add(ListForm("교촌","23","2/4","4000"))
        pageList.add(ListForm("123","23","2/4","4000"))
        pageList.add(ListForm("456","23","2/4","4000"))
        pageList.add(ListForm("456","23","2/4","4000"))
        pageList.add(ListForm("456","23","2/4","4000"))
        pageList.add(ListForm("456","23","2/4","4000"))
        return pageList
    }
}


class ListAdapter(val list:List<ListForm>) : RecyclerView.Adapter<ListAdapter.Holder>(){
    class Holder (val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root){
        fun setItem(listform: ListForm){
            with(binding){
                itemTitle.text=listform.title
                itemEndTime.text=listform.title
                itemPeople.text=listform.people
                itemDeliveryCharge.text=listform.deliverycharge
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding=ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)

    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setItem(list.get(position))
    }

    override fun getItemCount() = list.size

}
data class ListForm (var title:String, var endtime:String, var people:String, var deliverycharge:String)
