package com.example.pharmaplat.user

import android.app.Dialog
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pharmaplat.DataModel.ReviewsData
import com.example.pharmaplat.DataModel.UserProfileData
import com.example.pharmaplat.R
import com.example.pharmaplat.databinding.ActivityUserProfileBinding
import com.example.pharmaplat.recycleViewAdapters.ProfileReviewAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class UserProfile : AppCompatActivity() {

    // View Binder
    private lateinit var binding: ActivityUserProfileBinding

    // Firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference

    private lateinit var dialog: Dialog
    private lateinit var user: UserProfileData
    private lateinit var uid: String

    // Review recycler Initialise
    private lateinit var reviewAdapter: ProfileReviewAdapter
    private lateinit var reviewLayout: LinearLayoutManager
    private lateinit var reviewRecyclerView: RecyclerView

    // Review mock data
    private var reviewsList: MutableList<ReviewsData> = mutableListOf(
        ReviewsData("Selam WoldeYohanes", 4.0F,"11 days back", "Good customer service", R.drawable.rediet),
        ReviewsData("Ismael Mohamed", 4.0F,"21 days back", "Trustworthy!", R.drawable.jonah),
        ReviewsData("Nebyu Samuel", 3.0F,"2 months back", "Good over all but improve time managment.", R.drawable.abate),
        ReviewsData("Hanico", 2.0F, "3 months back", "Not responsive", R.drawable.koka),
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Review recycler hook
        reviewRecyclerView = findViewById(R.id.profile_review_recycler)
        reviewLayout = LinearLayoutManager(this)
        reviewAdapter = ProfileReviewAdapter(reviewsList)

        // Review recycler  attach adapter & layout
        reviewRecyclerView.adapter = reviewAdapter
        reviewRecyclerView.layoutManager = reviewLayout

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        if (uid.isNotEmpty()){
            getUserData()
        }

    }

    private fun getUserData() {

        showProgressBar()
        databaseReference.child(uid).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(UserProfileData::class.java)!!
                binding.profileFullName.text = user.fullName
                binding.profileUserName.text = user.userName
                binding.profileBio.text = user.bio

                getUserPicture()
            }

            override fun onCancelled(error: DatabaseError) {
                hideProgressBar()
               Toast.makeText(this@UserProfile, "Failed to get Profile Data.", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getUserPicture() {
        storageReference = FirebaseStorage.getInstance().reference.child("Users/$uid.jpg")
        val downloadUrl = storageReference.downloadUrl.toString()
        Glide.with(this)
            .load(downloadUrl)
            .circleCrop()
            .placeholder(R.drawable.ic_person_24)
            .into(binding.profileImage)

    /*    val localFile = File.createTempFile("tempImage", "jpg")
        getFile(localFile).addOnSuccessListener {

            val bitemap = BitmapFactory.decodeFile(localFile.absolutePath)
            Glide.with(this)
                .load(bitemap)
                .circleCrop()
                .into(binding.profileImage)

            hideProgressBar()
        }.addOnFailureListener{

            hideProgressBar()
            Toast.makeText(this, "Failed To Download Picture", Toast.LENGTH_SHORT).show()
        }*/
    }




    private fun  showProgressBar(){
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