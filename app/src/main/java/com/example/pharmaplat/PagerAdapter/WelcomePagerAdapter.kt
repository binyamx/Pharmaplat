package com.example.pharmaplat.PagerAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pharmaplat.user.SignIn
import com.example.pharmaplat.user.SignUpFragment

class WelcomePagerAdapter (fa: FragmentActivity): FragmentStateAdapter(fa) {

    val pageNumber = 2
    override fun getItemCount(): Int  = pageNumber

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SignIn()
            1 -> SignUpFragment()
            else -> SignUpFragment()
        }
    }


}