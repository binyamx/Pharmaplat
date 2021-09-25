package com.example.pharmaplat.itemCatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.CategoryNames
import com.example.pharmaplat.R
import com.example.pharmaplat.recycleViewAdapters.categoryAdapters.SubSubCategoryNamesAdapter

class SubSubCategory : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SubSubCategoryNamesAdapter
    private lateinit var layout: LinearLayoutManager
    private lateinit var categoryTittle: TextView

    var antiUlcerAgentsCategoryList: MutableList<CategoryNames> = mutableListOf(

        CategoryNames("Cimetidine"),
        CategoryNames("Omeprazole"),
        CategoryNames("Ranitidine")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_sub_category)

        recyclerView = findViewById(R.id.sub_sub_category_name_list_recycle_view)
        adapter = SubSubCategoryNamesAdapter(antiUlcerAgentsCategoryList)
        layout = LinearLayoutManager(this)


        categoryTittle = findViewById(R.id.sub_sub_category_name_tittle)
        categoryTittle.text = intent.getStringExtra("subCategoryClicked")


        recyclerView.adapter = adapter
        recyclerView.layoutManager = layout


    }
}