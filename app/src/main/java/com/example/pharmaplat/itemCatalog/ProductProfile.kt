package com.example.pharmaplat.itemCatalog

import android.graphics.BitmapFactory
import android.net.Uri
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.pharmaplat.DataModel.BrowsableItemData
import com.example.pharmaplat.DataModel.UserProfileData
import com.example.pharmaplat.R
import com.example.pharmaplat.databinding.ActivityProductProfileBinding
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

private const val Tag = "ProductProfile"
class ProductProfile : AppCompatActivity() {

    private lateinit var binding: ActivityProductProfileBinding

    private lateinit var productTitle: String
    private lateinit var userName: String
    private lateinit var productPicture: Uri
    private lateinit var productDescription: String
    private lateinit var Uid: String

    //Firebase
    private lateinit var storageReference: StorageReference
    private lateinit var databaseReference: DatabaseReference




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        // Get Data

        productTitle = intent.getStringExtra("productTitle")!!
        Uid = intent.getStringExtra("productUid")!!
        productDescription = intent.getStringExtra("productDescription")!!
        productPicture = intent.getParcelableExtra<Uri>("productPicture")!!


        // If source is from search screen download user name else use intent
        if (intent.getBooleanExtra("SourceFromSearch", false) ){
            databaseReference.child(Uid)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        Log.d(Tag, "on data change : $snapshot")
                         val user = snapshot.getValue(UserProfileData::class.java)!!
                        Log.d(Tag, "user : $user")
                        userName = user.fullName!!
                        binding.companyNameTextView.text = userName
//
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.d(Tag, error.toString())

                        Toast.makeText(this@ProductProfile, "Failed to get Data.", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
        } else {
            userName = intent.getStringExtra("productUserName")!!
            binding.companyNameTextView.text = userName


        }
        // Set Up Views
        binding.productTitleTextView.text = productTitle

        binding.productDetailTextView.text = productDescription

        Glide.with(this)
            .load(productPicture)
            .into(binding.productProfilePic)

        Log.d(Tag, Uid)
        storageReference = FirebaseStorage.getInstance().reference.child("Users/$Uid.jpg")
        val localFile = File.createTempFile("tempImage", "jpg")
        storageReference.getFile(localFile).addOnSuccessListener {
            val bitemap = BitmapFactory.decodeFile(localFile.absolutePath)
            Glide.with(this)
                .load(bitemap)
                .circleCrop()
                .placeholder(R.drawable.ic_person_24)
                .into(binding.userProfileImageView)

            /*    binding.profileImage.setImageBitmap(bitemap)
                Log.d(TAG,it.toString())
                hideProgressBar()*/
        }.addOnFailureListener {

            Toast.makeText(this, "Failed To Download Picture", Toast.LENGTH_SHORT).show()
            Log.d(Tag, it.message.toString())

        }












    }
}