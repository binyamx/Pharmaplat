package com.example.pharmaplat.DataModel

import android.net.Uri

data class SearchData(
    val categoryName: String? = null,
    val productTitle: String? = null,
    val productDescription: String? = null,
    val uid: String? = null,
    val photoDownloadUri: String? = null,
    )