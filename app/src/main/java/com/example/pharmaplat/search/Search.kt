package com.example.pharmaplat.search

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.SearchData
import com.example.pharmaplat.DataModel.SearchResultDataModel
import com.example.pharmaplat.DataModel.UserProfileData
import com.example.pharmaplat.R
import com.example.pharmaplat.recycleViewAdapters.searchAdapter.SearchAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.item_recycler_market.view.*
import java.io.File
import java.util.*

private const val  TAG: String = "SearchActivity"
class Search : AppCompatActivity() {

    // Firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference

    private lateinit var user: UserProfileData
    private lateinit var userFullName: String
    private lateinit var productPic: Bitmap


    private  var searchList = mutableListOf<SearchResultDataModel>()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchRecycleView: RecyclerView
    private lateinit var searchLayout: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        auth = FirebaseAuth.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance()



        // set up recycler view
        searchRecycleView = findViewById(R.id.search_recycler)
        searchAdapter = SearchAdapter(searchList)
        searchLayout = LinearLayoutManager(this)

        searchRecycleView.layoutManager = searchLayout
        searchRecycleView.adapter = searchAdapter

        search_edit_text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Get value of field
                val searchText : String = search_edit_text.text.toString()

                // search in Firestore
                searchInFireStore(searchText.toLowerCase(Locale.ROOT))
            }

            override fun afterTextChanged(s: Editable?) {}

        })


    }

    private fun searchInFireStore(searchText: String) {
        // search query
        firebaseFirestore.collection("Posts").whereArrayContains("search_keywords", searchText).limit(10).get().addOnSuccessListener { it ->
                val documentList = it.documents
                documentList.forEach {
                    val productDocument = it
                    val uid = productDocument.get("uid").toString()
                    val postTitle = productDocument.get("postTitle").toString()
                    val photoDownloadUri = productDocument.get("photoDownloadUri").toString()
                    val fullName =  getUserFullName(uid)
                    val productPicBitmap = getProductPicture(photoDownloadUri, uid, postTitle)

                    searchList?.plusAssign(SearchResultDataModel(postTitle,fullName,productPicBitmap))
                }

                searchAdapter = SearchAdapter(searchList!!)
                searchAdapter.notifyDataSetChanged()

            }

    }

    private fun getProductPicture(photoDownloadUri: String?, uid: String?, postTitle: String?): Bitmap {
        storageReference = FirebaseStorage.getInstance().reference
            .child("Products/$uid$postTitle.jpg")
        val localFile = File.createTempFile("tempImage", "jpg")
        storageReference.getFile(localFile).addOnSuccessListener {
            productPic = BitmapFactory.decodeFile(localFile.absolutePath)
        }

        return productPic
    }


    private fun getUserFullName(uid: String?) : String{

        databaseReference.child(uid!!).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(UserProfileData::class.java)!!
                userFullName = user.fullName.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                return
            }
        })


        return userFullName
   }

    override fun onStart() {
        super.onStart()

        // check if user is logged in, required for accessing the  database

        if (auth.currentUser == null) {
            // create new user
            auth.signInAnonymously().addOnCompleteListener {
                if (!it.isSuccessful){
                    Log.d(TAG,"Error: ${it.exception!!.message}")
                }
            }
        }
    }
}