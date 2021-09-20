package com.example.pharmaplat.LoginSignup

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import com.example.pharmaplat.R

class LoginSignup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_login_signup)
    }
    fun callLoginScreen(view: View){



          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

              val intent = Intent(this, Login::class.java)
              val login = findViewById<Button>(R.id.login_button)

              val options = ActivityOptions.makeSceneTransitionAnimation(this, login,
              getString(R.string.transition_name))

              startActivity(intent, options.toBundle())

              }
           else {
              // Swap without transition
             intent = Intent(this, Login::class.java)
              startActivity(intent)

          }

      }

    fun callSignupScreen (view: View){

        val intent = Intent(this, SignUp::class.java)

        val signup = findViewById<Button>(R.id.sign_up_btn)

         if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
             val options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                signup,
                "transition_signUp"
             )

             startActivity(intent, options.toBundle())

        } else {
            startActivity(intent)
        }




    }




    }
