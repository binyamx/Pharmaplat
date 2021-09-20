package com.example.pharmaplat.user

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import com.example.pharmaplat.LoginSignup.Signup2ndClass
import com.example.pharmaplat.R
import com.example.pharmaplat.databinding.ActivityUploadPictureBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class UploadPicture : AppCompatActivity() {

    private val TAG: String = "UploadPicture"

    private lateinit var binding : ActivityUploadPictureBinding

    // Intent Origin value
    private lateinit var value: String

    // Progress Dialog
    private lateinit var dialog: Dialog

    // Firebase
    private lateinit var auth: FirebaseAuth
    private  var user: FirebaseUser? = null
    private lateinit var storageReference: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadPictureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hooks For Firebase
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser


        // Receive Uri intent and bind to circularImageView
        val pictureUri = intent.getParcelableExtra<Uri>("picture")
        Log.d(TAG, pictureUri.toString())
        binding.circularImageView.setImageURI(pictureUri)
        // Receive Uri origin and bind to Text View
        value = intent.getStringExtra("intentOrigin").toString()
        if (value == "SignUp2ndClass"){
            binding.uploadButton.text = getString(R.string.select)
        }


        binding.uploadButton.setOnClickListener {

            if (value == "MyProfile"){
                showProgressBar()

                storageReference = FirebaseStorage.getInstance()
                    .getReference("Users/" + user?.uid + ".jpg")
                storageReference.putFile(pictureUri!!).addOnSuccessListener {
                    Toast.makeText(this, "Photo uploaded successfully.", Toast.LENGTH_SHORT).show()
                    hideProgressBar()
                    val intent = Intent(this, MyProfile::class.java)
                    startActivity(intent)

                }.addOnFailureListener {
                    Toast.makeText(this, "Uploading Photo Failed. Please try Again.", Toast.LENGTH_LONG).show()
                    hideProgressBar()
                    val intent = Intent(this, MyProfile::class.java)
                    startActivity(intent) }
            } else{
                val intent = Intent(this, Signup2ndClass::class.java)
                intent.putExtra("pictureUri", pictureUri)
                startActivity(intent)
            }
        }


    }


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