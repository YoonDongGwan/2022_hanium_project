package com.hanium.SJnJH

import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hanium.databinding.ActivityDeliveryStoresBinding
import com.google.android.material.tabs.TabLayoutMediator

class DeliveryStoresActivity : AppCompatActivity() {

    val binding by lazy{ActivityDeliveryStoresBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val getIntent = getIntent().getIntExtra("CATEGORY",1)
<<<<<<< HEAD
        val user_id=getIntent().getIntExtra("UID",0)
=======

>>>>>>> dcf29a1125f7a14f467e5710801b6733868c6991
        val list = listOf(
            ChickenFragment(),
            PizzaFragment(),
            HamburgerFragment(),
            ChineseFoodFragment(),
            WesternFoodFragment(),
            RestFoodFragment()
        )
        val pagerAdapter = FragmentPagerAdapter(list,this)
        binding.viewPager.adapter=pagerAdapter
        binding.deliveryStoresBackBtn.setOnClickListener{
            finish()
        }
        val titles = listOf("치킨","피자","햄버거","중식","양식","기타")
        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position->
            tab.text = titles.get(position)
        }.attach()
        binding.viewPager.currentItem = getIntent
    }
}
class FragmentPagerAdapter(val fragmentList : List<Fragment>, fragmentActivity : FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = fragmentList.size
    override fun createFragment(position: Int) = fragmentList.get(position)

}

data class StoreInform(val image: Drawable?, val name:String, val minPrice:Int, val deliveryTip : Int)