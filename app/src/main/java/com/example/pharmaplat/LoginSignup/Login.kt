package com.example.pharmaplat.LoginSignup

import android.content.ContentValues.TAG
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import com.example.pharmaplat.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rilixtech.widget.countrycodepicker.CountryCodePicker

class Login : AppCompatActivity() {

    private lateinit var countryCodePicker: CountryCodePicker
    private lateinit var phoneNumber: AppCompatEditText
    private lateinit var passWord: TextInputLayout
    private lateinit var progressBar: RelativeLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        phoneNumber = findViewById(R.id.login_phone_number1)
        countryCodePicker = findViewById(R.id.login_country_code_picker)

        passWord = findViewById(R.id.login_passWord)
        progressBar = findViewById(R.id.login_progress_bar)



    }

    fun loginFunction (view: View){

        /*
        * Validate phone number and password.
        */

        if (!validateInput()){
            return
        }

        progressBar.visibility = View.VISIBLE


        /*
        * Get data. */

        var phoneValue = phoneNumber.text.toString().trim()
        val passwordValue = passWord.editText?.text.toString().trim()

        if (phoneValue[0] == '0'){
            phoneValue = phoneValue.substring(1)
        }

        countryCodePicker.registerPhoneNumberTextView(phoneNumber)
        val completePhoneNumber = "+" + countryCodePicker.fullNumber + phoneValue


    /*
    * Database
    */


        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                if(dataSnapshot.exists()){
                    phoneNumber.error = null

                    val systemPassword = dataSnapshot.child("completePhoneNumber").
                            child("password").getValue(String::class.java)

                    if (systemPassword != null) {
                        if (systemPassword.equals(passwordValue))
                            passWord.error = null
                        passWord.isErrorEnabled = false

                        progressBar.visibility = View.GONE

                        val fullName = dataSnapshot.child("completePhoneNumber").
                        child("fullName").getValue(String::class.java)

                        val phoneNumber = dataSnapshot.child("completePhoneNumber").
                        child("phoneNumber").getValue(String::class.java)

                        val birthDate = dataSnapshot.child("completePhoneNumber").
                        child("birthDate").getValue(String::class.java)

                        Toast.makeText(applicationContext, fullName +"\n"+phoneNumber+"\n"+birthDate,
                        Toast.LENGTH_SHORT).show()

                    } else {
                        progressBar.visibility = View.GONE

                        Toast.makeText(applicationContext, "Password doesn't match.",
                            Toast.LENGTH_SHORT).show()
                    }
                }else {
                    progressBar.visibility = View.GONE

                    Toast.makeText(applicationContext, "Phone number doesn't exist.",
                        Toast.LENGTH_SHORT).show()

            }
            }



            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())

                progressBar.visibility = View.GONE
                Toast.makeText(applicationContext, databaseError.message.toString(),
                Toast.LENGTH_SHORT).show()


            }
        }

        val checkUser = FirebaseDatabase.getInstance().getReference("Users")
            .orderByChild("phoneNumber").equalTo(completePhoneNumber)

        checkUser.addListenerForSingleValueEvent(postListener)






    }

    private fun validateInput(): Boolean{
        val phoneValue = phoneNumber.text.toString().trim()
        val passwordValue = passWord.editText?.text.toString().trim()

        if (phoneValue.isEmpty()){
            phoneNumber.error = "Phone Number should be filled."
            phoneNumber.requestFocus()
            return false
        } else if (passwordValue.isEmpty()){
            passWord.error = "Password should be filled."
            passWord.requestFocus()
            return false
        } else {
            passWord.error = null
            phoneNumber.error = null

            passWord.isErrorEnabled = false

            return true
        }
    }
}