package com.example.pharmaplat.ItemCatalog

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.CategoryNames
import com.example.pharmaplat.R
import com.example.pharmaplat.recycleViewAdapters.categoryAdapters.CategoryNamesAdapter

class CategoryNameList : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoryNamesAdapter
    private lateinit var layout: LinearLayoutManager
    private lateinit var categoryTittle: TextView


    var medicinesCategoryList: MutableList<CategoryNames> = mutableListOf(

        CategoryNames("Gastrointestinal Medicines"),
        CategoryNames("Cardiovascular Medicines"),
        CategoryNames("Respiratory Medicines"),
        CategoryNames("Anti-Infectives"),
        CategoryNames("Central Nervous System Medicines"),
        CategoryNames("Anesthesia Medicines"),
        CategoryNames("Musculoskeletal and Joint Disease Medicines"),
        CategoryNames("Vitamins"),
        CategoryNames("Antihistamines"),
        CategoryNames("Endocrine Disorder Medicines"),
        CategoryNames("Contraceptives"),
        CategoryNames("Gynecological and Obstetrics Medications"),
        CategoryNames("Blood Products and Medicines"),
        CategoryNames("Fluid, Electrolyte and Acid Base Balance Medicines"),
        CategoryNames("Immunomodulators"),
        CategoryNames("Antineoplastics and Supportive Medicines"),
        CategoryNames("Benign Prostate Hyperplasia Medicines"),
        CategoryNames("Ophthalmic Agents"),
        CategoryNames("Ear, Nose and Throat Preparations"),
        CategoryNames("Dermatological Agents"),
        CategoryNames("Vaccines"),
        CategoryNames("Sera and Immunoglobulin"),
        CategoryNames("Poisoning Antidotes and other Substances"),
        CategoryNames("Radiocontrast Media"),
        CategoryNames("Miscellaneous"),


        )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_name_list)


        recyclerView = findViewById(R.id.category_name_list_recycle_view)
        adapter = CategoryNamesAdapter(medicinesCategoryList)
        layout = LinearLayoutManager(this)


        categoryTittle = findViewById(R.id.category_name_title)
        categoryTittle.text = intent.getStringExtra("categoryTitle")


        recyclerView.adapter = adapter
        recyclerView.layoutManager = layout



    }
}