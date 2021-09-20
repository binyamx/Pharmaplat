package com.example.pharmaplat.PagerAdapter

import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pharmaplat.main_screen.DemandFragment
import com.example.pharmaplat.main_screen.Market
import com.example.pharmaplat.main_screen.NewsFeed

class MainPagerAdapter(var fa: FragmentActivity): FragmentStateAdapter(fa){

    private val pageNumber = 3

    override fun getItemCount(): Int = pageNumber

    override fun createFragment(position: Int): Fragment {
        return when(position)  {
            0 -> Market.newInstance()
            1 -> NewsFeed.newInstance()
            2 -> DemandFragment.newInstance()
            else -> Market.newInstance()
        }
    }


}