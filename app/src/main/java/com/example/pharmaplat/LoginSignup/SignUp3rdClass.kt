package com.example.pharmaplat.LoginSignup

import android.app.ActivityOptions
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pharmaplat.DataModel.UserProfileData
import com.example.pharmaplat.R
import com.example.pharmaplat.databinding.ActivitySignUp3rdClassBinding
import com.example.pharmaplat.main_screen.MainActivity
import com.example.pharmaplat.user.MyProfile
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.rilixtech.widget.countrycodepicker.CountryCodePicker
import java.util.concurrent.TimeUnit

class SignUp3rdClass : AppCompatActivity() {


    // View Binding
    private lateinit var binding: ActivitySignUp3rdClassBinding
    private lateinit var dialog: Dialog


    private lateinit var countryCodePicker: CountryCodePicker
    private lateinit var phoneNumber: TextInputLayout
    private val TAG: String = "SignUp3rdClass"


    // Firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var database: DatabaseReference
    private var user: FirebaseUser? = null
    private lateinit var storageReference: StorageReference

    private lateinit var storedVerificationId: String
    private lateinit var resendingToken: PhoneAuthProvider.ForceResendingToken



    private var fullName: String? = null
    private var userName: String? = null
    private var passWord: String? = null
    private var accountType: String? = null
    private var profileBio: String? = null
    private var userPicture: Uri? = null

//    private var birthDate: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUp3rdClassBinding.inflate(layoutInflater)
        setContentView(binding.root)


        countryCodePicker = findViewById(R.id.country_code_picker)
        phoneNumber = findViewById(R.id.phone_number_text)
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser


        // Data from the previous screen -> Sign up 2nd screen
        fullName = intent.getStringExtra("full_name")
        userName = intent.getStringExtra("user_name")
        passWord = intent.getStringExtra("pass_word")
        accountType = intent.getStringExtra("accountType")
        profileBio = intent.getStringExtra("profileBio")
        userPicture = intent.getParcelableExtra("pictureUri")

//         birthDate = intent.getStringExtra("age_picked")
        Log.i(TAG, "$fullName, $userName, $passWord , $accountType")


//       Send button click listener
        binding.sendCodeButton.setOnClickListener {

            // Validate Phone Number

            if (!validatePhoneNumber()){
                return@setOnClickListener
            }

            // Get Standard Phone number
            val phoneNumber = getStandardPhoneNumber()

            // Finally, Send code to phone number
            sendVerificationCodeToUser(phoneNumber)

        }



        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(ContentValues.TAG, "onVerificationCompleted:$credential")


                signInWithPhoneAuthCredential(credential)

                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }


            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(ContentValues.TAG, "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    Log.i(ContentValues.TAG, e.message.toString())
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    Log.i(ContentValues.TAG, e.message.toString())
                }

                // Show a message and update the UI

                Toast.makeText(
                    applicationContext, e.message.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(ContentValues.TAG, "onCodeSent:$verificationId")
                Toast.makeText(
                    applicationContext, "Please insert the code sent to your phone number.",
                    Toast.LENGTH_SHORT
                ).show()


                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendingToken = token

                val phoneNo = getStandardPhoneNumber()

                val intent = Intent(applicationContext, VerifyOTP::class.java)
                intent.putExtra("verificationID", storedVerificationId)
                intent.putExtra("resendingToken", resendingToken)

                intent.putExtra("full_name", fullName)
                intent.putExtra("user_name", userName)
                intent.putExtra("pass_word", passWord)
                intent.putExtra("accountType", accountType)
                intent.putExtra("profileBio", profileBio)
                intent.putExtra("pictureUri", userPicture)

//                intent.putExtra("age_picked", birthDate )

                intent.putExtra("phoneNumber", phoneNo)

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    val options = ActivityOptions.makeSceneTransitionAnimation(
                        this@SignUp3rdClass,
                        binding.enterPhoneDiscription,
                        "transition_verify_btn"
                    )
                    startActivity(intent, options.toBundle())

                } else {
                    startActivity(intent)
                }


            }

        }

    }


    // Functions for Send Code button click listener

    // 1. Vlidate phone number
    fun validatePhoneNumber (): Boolean{
        val value = phoneNumber.editText?.text.toString()

        if (value.isEmpty()){
            phoneNumber.error = "Field can't be empty."
            return false
        }
        else {
            phoneNumber.error = null
            phoneNumber.isErrorEnabled = false
            return true
        }


    }

    // 2. Get the phone number in a standard format
    fun getStandardPhoneNumber(): String {

        var userEnteredPhoneNumber = phoneNumber.editText?.text.toString().trim()

        if (userEnteredPhoneNumber[0] == '0') {
            userEnteredPhoneNumber = userEnteredPhoneNumber.substring(0)
        }

        return "+" + countryCodePicker.fullNumber + userEnteredPhoneNumber
    }

    // 3. Finally, Send code to phone number
    private fun sendVerificationCodeToUser(phoneNumber: String?) {

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber.toString())       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

    }


    // Three functions for when verification is Automatic

    // 1. Sign In
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
                    finish()


                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(
                            applicationContext, "The verification code entered was invalid",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                    // Update UI
                }
            }

    }

    // 2. Store Users Data in realtime database
    private fun storeNewUsersData(
        fullName: String, userName: String,
        phoneNumber: String, passWord: String, accountType: String, profileBio: String,

    ) {

        showProgressBar()
        val addNewUser = UserProfileData(fullName, userName, phoneNumber, passWord, accountType, profileBio)
        database = FirebaseDatabase.getInstance().getReference("Users")
        user?.uid?.let {
            database.child(it).setValue(addNewUser).addOnSuccessListener {
                Toast.makeText(this, "Successfully saved!", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to save data!", Toast.LENGTH_SHORT).show()
            }
        }
        uploadPicture(userPicture!!)
        Log.i(TAG, "$addNewUser")


    }

    // 3. Finally, Store user profile picture
    private fun uploadPicture(userPicture: Uri) {

        storageReference = FirebaseStorage.getInstance()
            .getReference("Users/" + user?.uid + ".jpg")
        storageReference.putFile(userPicture).addOnSuccessListener {
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


//     Verification is'nt Automatic ... when the next button is touched -> VerifyOTP screen comes/
    fun callVerifyOTPScreen(view: View){

        /*
        * Validate Phone Number
        */

//        if (!validatePhoneNumber()){
//            return
//        }

        /*
        * Get data from previous screens and this screen (phone number)
        */

//        val phoneNumber = getStandardPhoneNumber()

//        var userEnteredPhoneNumber = phoneNumber.editText?.text.toString().trim()
//
//        if (userEnteredPhoneNumber[0] == '0'){
//            userEnteredPhoneNumber = userEnteredPhoneNumber.substring(0)
//        }
//
//
//        val phoneNo = "+"+countryCodePicker.fullNumber+userEnteredPhoneNumber

//        val intent = Intent(this, VerifyOTP::class.java)

        /*
        * Send data to verify OTP screen
        */


//
//    intent.putExtra("full_name", fullName)
//    intent.putExtra("user_name", userName)
//    intent.putExtra("pass_word", passWord)
//    intent.putExtra("accountType", accountType)
//    intent.putExtra("profileBio", profileBio)
//    intent.putExtra("pictureUri", userPicture)

        /*
        * Send Phone verification number
        */

//        sendVerificationCodeToUser(phoneNumber)


        /*
        * Animation
        */

//        val nextButton = findViewById<Button>(R.id.send_code_button)
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            val options = ActivityOptions.makeSceneTransitionAnimation(
//                this,
//                nextButton,
//                "transition_verify_btn"
//            )
//            startActivity(intent, options.toBundle())
//
//        } else {
//            startActivity(intent)
//        }


    }


}