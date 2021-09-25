package com.example.pharmaplat.itemCatalog

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.BrowsableItemData
import com.example.pharmaplat.DataModel.SearchData
import com.example.pharmaplat.DataModel.UserProfileData
import com.example.pharmaplat.databinding.ActivityListForOneItemBinding
import com.example.pharmaplat.recycleViewAdapters.marketAdapters.MarketAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore

private const val Tag = "ListForOneItem"

class ListForOneItem : AppCompatActivity() {

    private lateinit var binding: ActivityListForOneItemBinding

    // Category name from Final category adapter
    private lateinit var categoryName: String

    // Firebase
    private var searchList = mutableListOf<SearchData>()
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var databaseReference: DatabaseReference
    private lateinit var user: UserProfileData

    // recycler things
    private val marketList: MutableList<BrowsableItemData> = mutableListOf()
    private var listAdapter: MarketAdapter = MarketAdapter(marketList)
    private lateinit var recyclerView: RecyclerView

    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListForOneItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressBar = binding.progressBar

        // Firebase hook
        auth = FirebaseAuth.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")


        // Set screen title with category name
        categoryName = intent.getStringExtra("categoryName")!!
        Log.d(Tag, categoryName)
        binding.titleTextView.text = categoryName

        // Recycler view
        recyclerView = binding.listForOneItemRecycleView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listAdapter

        // Search firebase
        loadProducts(categoryName)

    }

    private fun loadProducts(categoryName: String) {

        // search query
        progressBar.visibility = View.VISIBLE


        firebaseFirestore.collection("Products")
            .whereEqualTo("categoryName", categoryName)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {

                    searchList = it.result!!.toObjects(SearchData::class.java)
                    Log.d(Tag, "Search List data: $searchList")

                    if (searchList != emptyList<SearchData>()){
                        for (i in 0 until searchList.size) {

                            // Get full name, update market list and list adapter
                            getFullName(
                                searchList[i].uid,
                                searchList[i].productTitle,
                                searchList[i].photoDownloadUri!!.toUri()
                            )
                        }
                    } else {
                        Toast.makeText(this, "No Product has been listed.", Toast.LENGTH_LONG,).show()
                    }


                } else {
                    Log.d(Tag, "exception : ${it.exception!!.message}")
                    Toast.makeText(this, "Failed to get Data.", Toast.LENGTH_SHORT).show()
                }
            }


    }

    private fun getFullName(uid: String?, productTitle: String?, productPicture: Uri?) {

        var userFullName: String

        databaseReference.child(uid!!)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d(Tag, "on data change : $snapshot")
                    user = snapshot.getValue(UserProfileData::class.java)!!
                    Log.d(Tag, "user : $user")
                    userFullName = user.fullName!!

                    // update market list and list adapter
                    marketList.add(BrowsableItemData(productTitle, userFullName, productPicture))
                    Log.d(Tag, "market list after addition : $marketList")
                    progressBar.visibility = View.INVISIBLE
                    listAdapter.postList = marketList
                    listAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(Tag, error.toString())
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this@ListForOneItem, "Failed to get Data.", Toast.LENGTH_SHORT)
                        .show()
                }
            })


    }
}