package com.example.pharmaplat.LoginSignup

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chaos.view.PinView
import com.example.pharmaplat.DataModel.UserProfileData
import com.example.pharmaplat.R
import com.example.pharmaplat.databinding.ActivityVerifyOtpBinding
import com.example.pharmaplat.main_screen.MainActivity
import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class VerifyOTP : AppCompatActivity() {

    private lateinit var binding: ActivityVerifyOtpBinding

    private lateinit var pinFromUser: PinView
    lateinit var storedVerificationId: String
    private lateinit var dialog: Dialog


    private val TAG: String = "VerifyOTP"

    private var fullName: String? = null
    private var userName: String? = null
    private var passWord: String? = null
    private var accountType: String? = null
    private var phoneNumber: String? = null
    private var profileBio: String? = null
    private var userPicture: Uri? = null
//    private var birthDate: String? = null



    /*
    * Firebase staff hooks
    */
    private lateinit var auth: FirebaseAuth
    private var user: FirebaseUser? = null
    private lateinit var resendingToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var database: DatabaseReference
    private lateinit var storageReference: StorageReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // hooks
        pinFromUser = findViewById(R.id.pinView)

        auth = FirebaseAuth.getInstance()
        user = auth.currentUser

        fullName = intent.getStringExtra("full_name")
        userName = intent.getStringExtra("user_name")
        passWord = intent.getStringExtra("pass_word")
        accountType = intent.getStringExtra("accountType")
        phoneNumber = intent.getStringExtra("phoneNumber")
        profileBio = intent.getStringExtra("profileBio")
        userPicture = intent.getParcelableExtra<Uri>("pictureUri")
//        birthDate = intent.getStringExtra("age_picked")

        binding.enterSmsTextView.text = phoneNumber

        binding.verifyCodeButton.setOnClickListener {
            val code = pinFromUser.text.toString()

            storedVerificationId = intent.getStringExtra("verificationID").toString()
            val credential: PhoneAuthCredential =
                PhoneAuthProvider.getCredential(storedVerificationId, code)


            if (code.isNotEmpty()) {

                signInWithPhoneAuthCredential(credential)
            } else {
                Toast.makeText(
                    applicationContext, "Please fill in the code.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }




    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {

        auth.signInWithCredential(credential)
            .addOnCompleteListener(VerifyOTP()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    storeNewUsersData(
                        fullName.toString(),
                        userName.toString(),
                        phoneNumber.toString(),
                        passWord.toString(),
                        accountType.toString(),
                        profileBio.toString(),
                        )

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)


                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(applicationContext, "The verification code entered was invalid", Toast.LENGTH_SHORT).show()
                    }
                    // Update UI


                }
            }

    }

    private fun storeNewUsersData(
        fullName: String, userName: String,
        phoneNumber: String, passWord: String, accountType: String, profileBio: String,

    ) {

        showProgressBar()
        val addNewUser = UserProfileData(fullName, userName, phoneNumber, passWord, accountType, profileBio)
        Log.i(TAG, "$addNewUser")
        database = FirebaseDatabase.getInstance().getReference("Users")
        user?.uid?.let {
            database.child(it).setValue(addNewUser).addOnSuccessListener {
                Toast.makeText(this, "Successfully saved!", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Successfully saved!")
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to save data!", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Failed to save data!")
            }
        }
        uploadPicture(userPicture)



    }

    private fun uploadPicture(userPicture: Uri?){

        storageReference = FirebaseStorage.getInstance()
            .getReference("Users/" + user?.uid + ".jpg")
        storageReference.putFile(userPicture!!).addOnSuccessListener {
            Toast.makeText(this, "Photo uploaded successfully.", Toast.LENGTH_SHORT).show()
            hideProgressBar()


        }.addOnFailureListener {
            Toast.makeText(this, "Uploading Photo Failed. Please try Again.", Toast.LENGTH_LONG)
                .show()
            hideProgressBar()
        }
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