package com.example.pharmaplat.DataModel

class MostViewed () {


    var tittle: String? = null
    var rating: Float? = null
    var description: String? = null
    var picture: Int? = null

    constructor(tittle: String?, rating: Float?, description: String?, picture: Int?) :this(){

        this.tittle = tittle
        this.rating = rating
        this.description = description
        this.picture = picture

    }
}