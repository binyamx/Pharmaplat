package com.example.pharmaplat.LoginSignup

import android.app.ActivityOptions
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Pair
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.get
import com.example.pharmaplat.R
import com.example.pharmaplat.databinding.ActivitySignup2ndClassBinding
import com.example.pharmaplat.user.UploadPicture
import kotlinx.android.synthetic.main.activity_signup2nd_class.*
import java.util.*

class Signup2ndClass : AppCompatActivity() {

    val TAG = "SignuUp2nd"

    // View binding
    private lateinit var binding: ActivitySignup2ndClassBinding

    private lateinit var backButton: ImageView
    private lateinit var tittleTextView: TextView
    private lateinit var nextButton: Button
    private lateinit var loginButton: Button


//    private lateinit var ageDatePicker: DatePicker

    // Account type
    private lateinit var radioGroup: RadioGroup
    private lateinit var accountTypePicker: RadioButton

    private lateinit var pictureUri: Uri



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignup2ndClassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backButton = findViewById(R.id.sign_back_up_button)
        tittleTextView = findViewById(R.id.signup_tittle_text)
        nextButton = findViewById(R.id.signup_Next_button2)
        loginButton = findViewById(R.id.signup_login_button)

        radioGroup = findViewById(R.id.radiogroup)
//        ageDatePicker = findViewById(R.id.date_picker)

        val getImage = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) {

            pictureUri = it

            // Go to UploadPicture screen with the Uri
//            val intent = Intent(this, UploadPicture::class.java)
//            intent.putExtra("picture", it)
//            intent.putExtra("intentOrigin", "SignUp2ndClass")
//            startActivity(intent)



        }


        binding.addProfilePicture.setOnClickListener {
            getImage.launch("image/*")
        }

    }

    fun callNextSignup2ndScreen(view: View) {

        if ( !validateGender()){
            return
        }

        accountTypePicker = findViewById(radioGroup.checkedRadioButtonId)

        // Data from this Screen
        val accountType = accountTypePicker.text.toString()
        val profileBio = binding.profileBioText.editText?.text.toString()


        // Data from previous screen (Signup screen)
        val fullName = intent.getStringExtra("full_name")
        val userName = intent.getStringExtra("user_name")
        val passWord = intent.getStringExtra("pass_word")

        Log.d(TAG, "($fullName, $userName, $passWord, $accountType, $profileBio, $pictureUri)")



//        val year = ageDatePicker.year
//        val month = ageDatePicker.month
//        val day = ageDatePicker.dayOfMonth
//
//        val agePicked = day.toString() +"/"+month.toString()+"/"+year.toString()






        val intent = Intent(this, SignUp3rdClass::class.java)

        intent.putExtra("full_name", fullName )
        intent.putExtra("user_name", userName )
        intent.putExtra("pass_word", passWord )
        intent.putExtra("accountType", accountType )
        intent.putExtra("pictureUri",pictureUri )
        intent.putExtra("profileBio", profileBio)
//        intent.putExtra("age_picked", agePicked )

        //Add Transitionc

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                Pair.create(backButton, "transition_back_arrow_btn"),
                Pair.create(tittleTextView, "transition_tittle_text"),
                Pair.create(nextButton, "transition_next_btn"),
                Pair.create(loginButton, "transition_login_btn")
            )
            startActivity(intent, options.toBundle())
        } else {

            startActivity(intent)
        }


    }

    fun validateGender(): Boolean {
        if (radioGroup.checkedRadioButtonId == -1){
            Toast.makeText(this,"Please Select account type/", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }

    }

//    fun validateAge(): Boolean{
//        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
////        val userAge = ageDatePicker.year
//        val isAgeValid = currentYear - userAge
//
//        if (isAgeValid < 18){
//            Toast.makeText(this, "You have to be older than 18 to register.", Toast.LENGTH_SHORT ).show()
//            return false
//        } else {
//            return true
//        }
//    }
}