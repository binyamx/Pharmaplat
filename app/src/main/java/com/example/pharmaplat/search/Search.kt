package com.example.pharmaplat.search

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.CellClickListener
import com.example.pharmaplat.DataModel.SearchData
import com.example.pharmaplat.DataModel.SearchResultDataModel
import com.example.pharmaplat.R
import com.example.pharmaplat.databinding.ActivitySearchBinding
import com.example.pharmaplat.itemCatalog.ProductProfile
import com.example.pharmaplat.recycleViewAdapters.searchAdapter.SearchAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

private const val Tag: String = "SearchActivity"

class Search : AppCompatActivity(), CellClickListener {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var progressBar: ProgressBar

    // Firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseFirestore: FirebaseFirestore


    // recycler things
    private var searchList = mutableListOf<SearchData>()
    private  var searchAdapter: SearchAdapter = SearchAdapter(searchList, this)
    private lateinit var searchRecycleView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressBar = binding.progressBar
        progressBar.visibility = View.INVISIBLE

        auth = FirebaseAuth.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance()


        // set up recycler view
        searchRecycleView = binding.searchRecycler
        searchRecycleView.layoutManager = LinearLayoutManager(this)
        searchRecycleView.adapter = searchAdapter

        binding.searchEditText.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Get value of field
                val searchText: String =
                    binding.searchEditText.text.toString().toLowerCase(Locale.ROOT)

                // search in Firebase
                progressBar.visibility = View.VISIBLE
                searchInFireStore(searchText)
                Log.d(Tag, "searchText: $searchText")
            }

            override fun afterTextChanged(s: Editable?) {}

        })


    }

    private fun searchInFireStore(searchText: String) {

        // search query
        firebaseFirestore.collection("Products")
            .whereArrayContains("searchKeyWords", searchText)
            .limit(10).get()
            .addOnCompleteListener {
                if (it.isSuccessful) {

                    // Get the list and set it to adapter
                    searchList = it.result!!.toObjects(SearchData::class.java)
                    Log.d(Tag, "Search List data: $searchList")
                    progressBar.visibility = View.INVISIBLE
                    searchAdapter.searchResultList = searchList
                    searchAdapter.notifyDataSetChanged()

                } else {
                    progressBar.visibility = View.INVISIBLE
                    Log.d(Tag, "exception : ${it.exception!!.message}")
                    Toast.makeText(this, "Failed to get Data.", Toast.LENGTH_SHORT).show()

                }
            }
    }

    override fun onStart() {
        super.onStart()

        // check if user is logged in, required for accessing the  database

        if (auth.currentUser == null) {
            // create new user
            auth.signInAnonymously().addOnCompleteListener {
                if (!it.isSuccessful) {
                    Log.d(TAG, "Error: ${it.exception!!.message}")
                }
            }
        }
    }

    override fun OnClickListener(cell: Int) {

        val selectedPrdTitle = searchList[cell].productTitle
        val selectedPrdDescription = searchList[cell].productDescription
        val selectedPrdPicture = searchList[cell].photoDownloadUri!!.toUri()
        val selectedPrdUid = searchList[cell].uid



       val intent = Intent(this, ProductProfile::class.java)

        intent.putExtra("productTitle", selectedPrdTitle)
        intent.putExtra("productUid", selectedPrdUid)
        intent.putExtra("productDescription", selectedPrdDescription)
        intent.putExtra("productPicture", selectedPrdPicture)
        intent.putExtra("SourceFromSearch", true)

        startActivity(intent)



    }
}