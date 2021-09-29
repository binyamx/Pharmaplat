package com.example.pharmaplat.DataModel

import android.net.Uri

data class BrowsableItemData(
    val productTitle: String? = null,
    val userFullName: String? = null,
    val uid: String? = null,
    val productDescription: String? = null,
    val productPicture: Uri? = null,
//    val userProfilePicture: String? = null,
)
