package com.example.pharmaplat

import com.example.pharmaplat.DataModel.CategoryNames

interface LoadFinalRecycler{

    fun updateFinalCategory (categoryName: String, subCategoryList: MutableList<CategoryNames>)
}