package com.example.pharmaplat.DataModel

import android.net.Uri

data class UserProfileData(
    var fullName: String? = null,
    var userName: String? = null,
    var phoneNumber: String? = null,
    var password: String? = null,
    var accountType: String? = null,
    var bio: String? = null,
    var userPicture: String? = null
//    var birthDate: String? = null,
    ){

    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}



