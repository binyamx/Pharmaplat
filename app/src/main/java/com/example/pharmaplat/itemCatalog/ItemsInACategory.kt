package com.example.pharmaplat.itemCatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.ItemsAndPostAmount
import com.example.pharmaplat.R
import com.example.pharmaplat.recycleViewAdapters.marketAdapters.CategoriesAndContentAmountAdapter

class ItemsInACategory : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoriesAndContentAmountAdapter
    private lateinit var layout: LinearLayoutManager
    private lateinit var categoryName: TextView





    var drugList: MutableList<ItemsAndPostAmount> = mutableListOf(
        ItemsAndPostAmount("Meropenem", "7 Posts"),
        ItemsAndPostAmount("Penicilin", "11 Posts"),
        ItemsAndPostAmount("Cefepim", "23 Posts"),
        ItemsAndPostAmount("Ceftriaxone", "4 Posts"),
        ItemsAndPostAmount("Tetracyline", "9 Posts"),
        ItemsAndPostAmount("Ceftazidine", "17 Posts"),
        ItemsAndPostAmount("Vancomycine", "15 Posts"),
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_in_a_category)

        categoryName = findViewById(R.id.category_name_text_view)
        categoryName.text = intent.getStringExtra("subSubCategoryClicked")

        recyclerView = findViewById(R.id.items_list_recycler_view)
        adapter = CategoriesAndContentAmountAdapter(drugList)
        layout = LinearLayoutManager(this)

        recyclerView.layoutManager = layout
        recyclerView.adapter = adapter


    }
}