package com.example.pharmaplat.DataModel

class HotDeals (){

    var tittle: String? = null

    var description: String? = null
    var picture: Int? = null

    constructor(tittle: String?, description: String?, picture: Int?) :this(){

        this.tittle = tittle
        this.description = description
        this.picture = picture

    }
}