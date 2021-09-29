package com.example.pharmaplat.itemCatalog

import android.content.Intent
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
import com.example.pharmaplat.CellClickListener
import com.example.pharmaplat.DataModel.BrowsableItemData
import com.example.pharmaplat.DataModel.SearchData
import com.example.pharmaplat.DataModel.UserProfileData

import com.example.pharmaplat.databinding.ActivityProductsInFinalCategoryBinding
import com.example.pharmaplat.recycleViewAdapters.marketAdapters.MarketAdapter
import com.example.pharmaplat.search.Search
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore

private const val Tag = "ProductsInFinalCategory"

class ProductsInFinalCategory : AppCompatActivity(), CellClickListener {

    private lateinit var binding: ActivityProductsInFinalCategoryBinding

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
    private var listAdapter: MarketAdapter = MarketAdapter(marketList, this)
    private lateinit var recyclerView: RecyclerView

    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsInFinalCategoryBinding.inflate(layoutInflater)
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

        // when search relative layout is clicked move to search activity
        binding.searchRltLayout.setOnClickListener {
            val intent = Intent(this, Search::class.java)
            startActivity(intent)
        }

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

                    if (searchList != emptyList<SearchData>()) {
                        for (i in 0 until searchList.size) {

                            // Get full name, update market list and list adapter
                            addUserData(
                                searchList[i].uid,
                                searchList[i].productTitle,
                                searchList[i].productDescription,
                                searchList[i].photoDownloadUri!!.toUri()
                            )
                        }
                    } else {
                        Log.d(Tag, "Search List data: $searchList")
                        Toast.makeText(this, "Failed to get Data.", Toast.LENGTH_LONG).show()
                        progressBar.visibility = View.INVISIBLE }

                } else {
                    progressBar.visibility = View.INVISIBLE
                    Log.d(Tag, "exception : ${it.exception!!.message}")
                    Toast.makeText(this, "Failed to get Data.", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                progressBar.visibility = View.INVISIBLE
                Log.d(Tag, "exception : ${it.message}")
                Toast.makeText(this, "Failed to get Data.", Toast.LENGTH_SHORT).show()
            }
    }

    private fun addUserData(uid: String?, productTitle: String?, productDescription: String?, productPicture: Uri?) {

        var userFullName: String?
        var userProfilePicture: String?

        databaseReference.child(uid!!)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d(Tag, "on data change : $snapshot")
                    user = snapshot.getValue(UserProfileData::class.java)!!
                    Log.d(Tag, "user : $user")
                    userFullName = user.fullName!!
//                    userProfilePicture = user.userPicture


                    // update market list and list adapter
                    marketList.add(BrowsableItemData(
                        productTitle, userFullName, uid,
                        productDescription, productPicture))
                    Log.d(Tag, "market list after addition : $marketList")
                    progressBar.visibility = View.INVISIBLE
                    listAdapter.postList = marketList
                    listAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(Tag, error.toString())
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this@ProductsInFinalCategory, "Failed to get Data.", Toast.LENGTH_SHORT)
                        .show()
                }
            })


    }

    // Go to selected product profile with data.
    override fun OnClickListener(cell: Int) {

        // Get data
        val selectedPrdTitle = marketList[cell].productTitle
        val selectedPrdUserNames = marketList[cell].userFullName
        val selectedPrdDescription = marketList[cell].productDescription
        val selectedPrdPicture = marketList[cell].productPicture
        val selectedPrdUid = marketList[cell].uid

        val intent = Intent(this, ProductProfile::class.java)

        intent.putExtra("SourceFromSearch", false)
        intent.putExtra("productTitle", selectedPrdTitle)
        intent.putExtra("productUserName", selectedPrdUserNames)
        intent.putExtra("productUid", selectedPrdUid)
        intent.putExtra("productDescription", selectedPrdDescription)
        intent.putExtra("productPicture", selectedPrdPicture)


        startActivity(intent)
    }
}