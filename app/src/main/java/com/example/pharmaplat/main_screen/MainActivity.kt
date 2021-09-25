package com.example.pharmaplat.main_screen

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.SimpleDrawerListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.pharmaplat.itemCatalog.CategoryMedicines
import com.example.pharmaplat.LoginSignup.LoginSignup
import com.example.pharmaplat.R
import com.example.pharmaplat.databinding.ActivityMainBinding
import com.example.pharmaplat.user.MyProfile
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser



class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    // View binding
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewPager: ViewPager2
    private lateinit var contentView: RelativeLayout

    // Drawer animation constant
    private var EndScale = 0.7f


    private lateinit var bubbleNavigationView: BubbleNavigationConstraintView

    // Firebase
    private lateinit var auth: FirebaseAuth
    private   var user : FirebaseUser? = null


    //Drawer Menu

    private lateinit var drawerLayout : DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var menuIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



//        tabLayout = findViewById(R.id.tab_layout_main)
        viewPager = findViewById(R.id.view_pager_main)
        bubbleNavigationView = binding.mainBottomNavBar

        // Firebase hooks
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser




        contentView = findViewById(R.id.content)

        // Menu hooks
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        menuIcon = findViewById(R.id.drawer_icon)


        // Bottom / Chip Navigation Bar
        setViewPagerAdapter()
        setBottomNavigation()
        setViewPagerListener()

        // Dissable swiping of view pager
        viewPager.isUserInputEnabled = false


        //    Go To Login/Signup Screen or Post Dialog
        binding.addIcon.setOnClickListener {

           if (user == null){
               val intent = Intent(this, LoginSignup::class.java)
               startActivity(intent)
           } else {
               val intent = Intent(this, CategoryMedicines::class.java)
               intent.putExtra("categoryTitle", "Pick a Category")
               startActivity(intent)
           }

        }

            // Open Navigation Drawer
        navigationDrawer()
        binding.drawerIcon.setOnClickListener {
            if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_profile -> {
                    val intent = Intent(this, MyProfile::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_signOut-> {
                    auth.signOut()
                    true
                }


                else -> false

            }
        }

    }

    private fun setViewPagerAdapter() {
        val pagerAdapter = FragmentSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter
    }
    private fun setBottomNavigation() {

        bubbleNavigationView.setNavigationChangeListener { _, position ->
            when (position) {

                0 -> viewPager.currentItem = 0
                1 -> viewPager.currentItem = 1
                2 -> viewPager.currentItem = 2

            }
        }

    }
    private fun setViewPagerListener() {
        viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

//            override fun onPageSelected(position: Int) {
//                when (position){
//                    0 -> expandableBottemBar.select
//                         chipNavigationBar.setItemSelected(R.id.feed_bottom_nav_bar)
//                    1 -> chipNavigationBar.setItemSelected(R.id.market_bottom_nav_bar)
//                    2 -> chipNavigationBar.setItemSelected(R.id.demand_bottom_nav_bar)
//                    else -> R.id.market_bottom_nav_bar
//
//                }
//            }
        })
    }



    class FragmentSlidePagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
        private val pageNumber = 3

        override fun getItemCount(): Int = pageNumber

        override fun createFragment(position: Int): Fragment {
            return when(position)  {
                0 -> Market.newInstance()
                1 ->  NewsFeed.newInstance()
                2 -> DemandFragment.newInstance()
                else -> Market.newInstance()
            }
        }

    }



    // Navigation Drawer Functions (4)

    private fun navigationDrawer() {
        // Navigation Drawer
        navigationView.bringToFront()
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.setCheckedItem(R.id.nav_home)

        animateNavigationDrawer()


    }

    private fun animateNavigationDrawer() {


        drawerLayout.addDrawerListener(object : SimpleDrawerListener() {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                // Scale the View based on current slide offset
                val diffScaledOffset: Float = slideOffset * (1 - EndScale)
                val offsetScale = 1 - diffScaledOffset
                contentView.scaleX = offsetScale
                contentView.scaleY = offsetScale

                // Translate the View, accounting for the scaled width
                val xOffset = drawerView.width * slideOffset
                val xOffsetDiff: Float = contentView.width * diffScaledOffset / 2
                val xTranslation = xOffset - xOffsetDiff
                contentView.translationX = xTranslation
            }
        })
    }

    override fun onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }







}