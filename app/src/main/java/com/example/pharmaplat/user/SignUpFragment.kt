package com.example.pharmaplat.user

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.pharmaplat.R
import com.rilixtech.widget.countrycodepicker.CountryCodePicker

class SignUpFragment : Fragment() {

    lateinit var countryCodePicker: CountryCodePicker
    lateinit var phoneNumber: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        countryCodePicker = view.findViewById(R.id.ccp)!!
        phoneNumber = view.findViewById(R.id.phoneNumber)!!



        fun validatePhoneNo(): Boolean {
            val value = phoneNumber.text.toString()

            if (value.isEmpty()) {
                phoneNumber.setError("Field can't be empty.")
                return false
            } else {
                phoneNumber.setError(null)
                return true
            }
        }

        // Inflate the layout for this fragment
        return view

    }


}

