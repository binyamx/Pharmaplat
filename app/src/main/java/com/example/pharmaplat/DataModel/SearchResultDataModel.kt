package com.example.pharmaplat.DataModel

import android.graphics.Bitmap

data class SearchResultDataModel(
    val productTitle: String? = null,
    val fullName: String? = null,
    val productPictureUri: Bitmap? = null,
)
