package com.example.pharmaplat

import com.example.pharmaplat.DataModel.SubCategoryData

interface UpdateRecyclerView {

    fun callback(position: Int, item: MutableList<SubCategoryData>)
}