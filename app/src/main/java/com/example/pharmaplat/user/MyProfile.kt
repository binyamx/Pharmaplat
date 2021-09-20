package com.example.pharmaplat.user

import android.app.Dialog
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.ReviewsData
import com.example.pharmaplat.DataModel.UserProfileData
import com.example.pharmaplat.R
import com.example.pharmaplat.databinding.ActivityMyProfileBinding
import com.example.pharmaplat.databinding.DialogUpdateTextFieldBinding
import com.example.pharmaplat.recycleViewAdapters.ProfileReviewAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.dialog_update_text_field.*
import java.io.File
import java.util.*



class MyProfile : AppCompatActivity() {


    private var TAG = "MyProfile"

    // View Binder
    private lateinit var binding: ActivityMyProfileBinding
    private lateinit var updateDialogBinding: DialogUpdateTextFieldBinding

    // Firebase
    private lateinit var auth: FirebaseAuth
    private var user: FirebaseUser? = null
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference

    private lateinit var dialog: Dialog
    private lateinit var userData: UserProfileData


    // Review recycler Initialise
    private lateinit var reviewAdapter: ProfileReviewAdapter
    private lateinit var reviewLayout: LinearLayoutManager
    private lateinit var reviewRecyclerView: RecyclerView

    // Update profile fullName or bio
    private lateinit var updateDialog: Dialog
    private lateinit var updateValue: String
    private lateinit var textHashMap: Map<String, String>




    //     Review mock data
    private var reviewsList: MutableList<ReviewsData> = mutableListOf(
        ReviewsData(
            "Selam WoldeYohanes",
            4.0F,
            "11 days back",
            "Good customer service",
            R.drawable.rediet
        ),
        ReviewsData("Ismael Mohamed", 4.0F, "21 days back", "Trustworthy!", R.drawable.jonah),
        ReviewsData(
            "Nebyu Samuel",
            3.0F,
            "2 months back",
            "Good over all but improve time managment.",
            R.drawable.abate
        ),
        ReviewsData("Hanico", 2.0F, "3 months back", "Not responsive", R.drawable.koka),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Review recycler hook
        reviewRecyclerView = binding.profileReviewRecycler
        reviewLayout = LinearLayoutManager(this)
        reviewAdapter = ProfileReviewAdapter(reviewsList)

        // Review recycler  attach adapter & layout
        reviewRecyclerView.adapter = reviewAdapter
        reviewRecyclerView.layoutManager = reviewLayout

        auth = FirebaseAuth.getInstance()
        user = auth.currentUser

        //updateDialogBinding = DialogUpdateTextFieldBinding.inflate(layoutInflater.from(getConte))


        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        // Download user profile data
        if (user != null) {
            getUserData()
        }

            // Deal with the selected image -> Upload
        val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) {

            showProgressBar()

            storageReference = FirebaseStorage.getInstance().getReference("Users/" + user?.uid + ".jpg")
            storageReference.putFile(it!!).addOnSuccessListener {
                Toast.makeText(this, "Photo uploaded successfully.", Toast.LENGTH_SHORT).show()
                getUserPicture()
            }.addOnFailureListener {
                Toast.makeText(this, "Uploading Photo Failed. Please try Again.", Toast.LENGTH_LONG)
                    .show()
                hideProgressBar()
            }
        }

        // 3 functions related with updating user profile
        binding.editProfileImage.setOnClickListener {

            getImage.launch("image/*")
        }
        binding.editProfileUserName.setOnClickListener {
            updateValue = "fullName"
            editTextField()
        }
        binding.editBio.setOnClickListener {
            updateValue = "bio"
            editTextField()
        }


        // Update button on update Text dialog
//        update_text_button.setOnClickListener {
//            updateTextField()
//        }
    }

    // Edit Profile Data
    private fun editTextField() {

        updateDialog = Dialog(this)
        if (updateValue == "fullName") {
            dialog.setTitle("Full Name")
        } else {
            dialog.setTitle("Bio")
        }

        updateDialog.setContentView(R.layout.dialog_update_text_field)
        updateDialog.setCanceledOnTouchOutside(true)
        updateDialog.update_text_button.setOnClickListener {
            updateTextField()
        }
        updateDialog.show()
    }
    private fun updateTextField() {
        showProgressBar()
        val newText = updateDialog.full_name_text.editText?.text.toString().trim()
        textHashMap = if (updateValue == "fullName") {
            mapOf(
                "fullName" to newText
            )
        } else {
            mapOf(
                "bio" to newText
            )
        }

        databaseReference.child(auth.currentUser?.uid.toString())
            .updateChildren(textHashMap).addOnSuccessListener {
                hideProgressBar()
                hideEditDialog()
                Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                hideProgressBar()
                hideEditDialog()
                Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show()

            }
    }

    private fun hideEditDialog() {
        updateDialog.dismiss()
    }


    // Download User profile Data
    private fun getUserData() {

        showProgressBar()

        databaseReference.child(user?.uid!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userData = snapshot.getValue(UserProfileData::class.java)!!
                binding.profileFullName.text = userData.fullName
                binding.profileUserName.text = getString(R.string.userName, userData.userName)
                binding.profileBio.text = userData.bio
                getUserPicture()
                hideProgressBar()
            }

            override fun onCancelled(error: DatabaseError) {
                hideProgressBar()
               Toast.makeText(this@MyProfile, "Failed to get Profile Data.", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }

    private fun getUserPicture() {
        storageReference = FirebaseStorage.getInstance().reference.child("Users/${user?.uid}.jpg")

        val localFile = File.createTempFile("tempImage", "jpg")
        storageReference.getFile(localFile).addOnSuccessListener {

            val bitemap = BitmapFactory.decodeFile(localFile.absolutePath)
            binding.profileImage.setImageBitmap(bitemap)
            Log.d(TAG,it.toString())
//            hideProgressBar()
        }.addOnFailureListener {

//            hideProgressBar()
            Toast.makeText(this, "Failed To Download Picture", Toast.LENGTH_SHORT).show()
        }
    }

//      Progress Bar
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
