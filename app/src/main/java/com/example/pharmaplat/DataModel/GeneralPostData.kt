package com.example.pharmaplat.DataModel

class GeneralPostData (){

    var fullName: String? = null
    var reviewRating: Float? = null
    var reviewAmount: Int? = null
    var postTitle: String? = null
    var postDetail: String? = null
    var userPicture: Int? = null
    var postPicture: Int? = null

    constructor(fullName: String, reviewRating: Float?, reviewAmount: Int?, postTitle: String?, postDetail: String?, userPicture: Int?, postPicture: Int?): this(){

        this.fullName = fullName
        this.reviewRating = reviewRating
        this.reviewAmount = reviewAmount
        this.postTitle = postTitle
        this.postDetail = postDetail
        this.userPicture = userPicture
        this.postPicture = postPicture
                }



}