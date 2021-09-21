package com.example.pharmaplat.createPost

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.pharmaplat.R
import com.example.pharmaplat.databinding.ActivityCreatePostBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*
private const val TAG = "CreatePost"
class CreatePost : AppCompatActivity() {



    private lateinit var dialog: Dialog

    // Initialize data vriables
    private lateinit var pictureUri: Uri
    private lateinit var postTitle: String
    private lateinit var postDescription: String
    private lateinit var categoryName: String

    // Firebase
    private lateinit var auth: FirebaseAuth
    private var user: FirebaseUser? = null
    private lateinit var storageReference: StorageReference
    private lateinit var firestore: FirebaseFirestore



    // Decide what to do with the selected picture
    private val getImage = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ){
        pictureUri = it
    }

    private lateinit var binding: ActivityCreatePostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hooks for data variables
        categoryName = intent.getStringExtra("categoryName").toString()

        // Category name textview
        binding.categoryName.text = getString(R.string.category_name
            ,categoryName.toLowerCase(Locale.ROOT).capitalize(Locale.ROOT)
        )

        // Firebase hooks
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser
        firestore = FirebaseFirestore.getInstance()

        // Add Image click listener
        binding.addPicture.setOnClickListener {

            getImage.launch("image/*")
        }

        //  Send to Firebase click listener
        binding.createPostButton.setOnClickListener {

            showProgressBar()
            uploadPicture(pictureUri)
        }




    }

    private fun uploadPicture(pictureUri: Uri?) {

        /* Hooks for data variables -- these need to be initialized after add post is clicked
         if initialized on create method their value will be null*/

        postTitle = binding.postTitle.editText?.text.toString()
        postDescription = binding.postDescription.editText?.text.toString()

        // Firebase storage for post image
        storageReference = FirebaseStorage.getInstance()
            .getReference("Products/" +user?.uid + postTitle+ ".jpg" )

        storageReference.putFile(pictureUri!!).addOnCompleteListener { it ->
            if (it.isSuccessful) {
                it.result.metadata!!.reference!!.downloadUrl.addOnSuccessListener {
                    val photoDownloadUri = it.toString()

                    addToFireStore(postTitle, postDescription, categoryName , photoDownloadUri)
                }

            } else {
                // Handle failures

                hideProgressBar()
                Log.d(TAG, it.toString())
                Toast.makeText(this, "Posting Failed. Try Again.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun addToFireStore(postTitle: String, postDescription: String, categoryName: String, photoDownloadUri: String) {

        val searchKeyWords = generateSearchKeyWords(postTitle)

        val postMap = hashMapOf(
            "categoryName" to categoryName,
            "productTitle" to postTitle,
            "productDescription" to postDescription,
            "uid" to user!!.uid,
            "photoDownloadUri" to photoDownloadUri,
            "searchKeyWords" to searchKeyWords,
        )

        firestore.collection("Posts")
            .add(postMap)
            .addOnSuccessListener {

                hideProgressBar()
                Log.d(TAG, postMap.toString())


            }.addOnFailureListener {
                hideProgressBar()
                Log.d(TAG, it.toString())
                Toast.makeText(this, "Posting Failed. Try Again.", Toast.LENGTH_SHORT).show()
            }


    }

    private fun generateSearchKeyWords(postTitle: String): List<String> {
        var postTitleLowerCase = postTitle.toLowerCase(Locale.ROOT)
        var keyWords = mutableListOf<String>()

        // Split all words from the string
        val words = postTitleLowerCase.split(" ")

        // For each word
        for (word in words){
            var appendString = ""

            // For every character in the whole string

            for (charPosition in postTitleLowerCase.indices){
                appendString += postTitleLowerCase[charPosition].toString()
                keyWords.add(appendString)
            }
            // remove first word from the string

            postTitleLowerCase = postTitleLowerCase.replace("$word ", "")
        }
        return  keyWords
    }

    // Progress dialog
    private fun showProgressBar() {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_progress)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun hideProgressBar() {
        dialog.dismiss()
    }



}