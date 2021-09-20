package com.example.pharmaplat.LoginSignup

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.pharmaplat.R
import com.google.android.material.textfield.TextInputLayout
import android.util.Pair as UtilPair

class SignUp : AppCompatActivity() {

    private lateinit var backButton: ImageView
    private lateinit var tittleTextView: TextView
    private lateinit var nextButton: Button
    private lateinit var loginButton: Button


    private lateinit var fullName: TextInputLayout
    private lateinit var userName: TextInputLayout
    private lateinit var password: TextInputLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        backButton = findViewById(R.id.sign_back_up_button)
        tittleTextView = findViewById(R.id.signup_tittle_text)
        nextButton = findViewById(R.id.signup_Next_button)
        loginButton = findViewById(R.id.signup_login_button)


        fullName = findViewById(R.id.full_name_text)
        userName = findViewById(R.id.user_name_text)
        password = findViewById(R.id.password_text)


    }

    fun callNextSignupScreen(view: View) {

        if (!validateFullName() || !validateUsername() || !validatePassword()) {
            return
        }

        val intent = Intent(this, Signup2ndClass::class.java)

        intent.putExtra("full_name", fullName.editText?.text.toString().trim())
        intent.putExtra("user_name", userName.editText?.text.toString().trim())
        intent.putExtra("pass_word", password.editText!!.text.toString().trim())

        //Add Transitions

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                UtilPair.create(backButton, "transition_back_arrow_btn"),
                UtilPair.create(tittleTextView, "transition_tittle_text"),
                UtilPair.create(nextButton, "transition_next_btn"),
                UtilPair.create(loginButton, "transition_login_btn")
            )
            startActivity(intent, options.toBundle())
        } else {

            startActivity(intent)
        }

    }


    /*
    * Validate input functions
    */


    private fun validateFullName(): Boolean {

        val value = fullName.editText?.text.toString().trim()

        return if (value.isEmpty()) {
            fullName.error = "Field can't be empty."
            false
        } else {
            fullName.error = null
            fullName.isErrorEnabled = false
            true
        }
    }

    private fun validateUsername(): Boolean {


        val value = userName.editText?.text.toString().trim()
        val checkspaces = "\\A\\w{1,15}\\z".toRegex()

        if (value.isEmpty()) {
            userName.error = "Field can't be empty."
            return false
        } else if (value.length > 15) {
            userName.error = "User name is too long."
            return false
        } else if (!value.matches(checkspaces)) {
            userName.error = "No White spaces are allowed"
            return false
        } else {
            userName.error = null
            userName.isErrorEnabled = false
            return true
        }
    }


    private fun validatePassword(): Boolean {

        val value = password.editText?.text.toString().trim()
        val checkPassword: Regex = ("^" +
//                "(?=.*[a-zA-Z])" +          // any Letter
//                "(?=.*[@#$%^&+=])" +        // at Least 1 special character
//               "(?=\\S+$)" +              // no white spaces
                ".{6,}" +                  // at least 4 characters
                "$").toRegex()


        if (value.isEmpty()) {
            password.error = "Field can't be empty."
            return false
        } else if (!value.matches(checkPassword)) {
            password.error = "Password should contain at least four characters!"
            return false
        } else {
            password.error = null
            password.isErrorEnabled = false
            return true
        }
    }
}



