package com.example.pharmaplat.itemCatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.CategoryNames
import com.example.pharmaplat.R
import com.example.pharmaplat.recycleViewAdapters.categoryAdapters.SubCategoryNamesAdapter

class SubCategoryNames(s: String, mutableListOf: MutableList<CategoryNames>) : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SubCategoryNamesAdapter
    private lateinit var layout: LinearLayoutManager
    private lateinit var categoryTittle: TextView

    var gastroIntestinalMedicines: MutableList<CategoryNames> = mutableListOf(

        CategoryNames("Antacids"),
        CategoryNames("Antiulcer agents"),
        CategoryNames("Upper GI bleeding Medicines"),
        CategoryNames("Antispasmodics/ Spasmolytics analgesics"),
        CategoryNames("Antiemetics"),
        CategoryNames("Laxatives and Cathartics "),
        CategoryNames("Diarrhea management Medicine"),
        CategoryNames("Antiflatulents"),
        CategoryNames("Antihaemorrhoidal Agents"),
        CategoryNames("Inflammatory Bowel Disease Medicines")


    )
    var cardioVascularMedicines: MutableList<CategoryNames> = mutableListOf(

        CategoryNames("Heart Failure Medicines"),
        CategoryNames("Antiarrhythmics"),
        CategoryNames("Antihypertensive"),
        CategoryNames("Diuretics"),
        CategoryNames("Angina/Ischemic Heart Disease Medicines"),
        CategoryNames("Vascular shock Medicine"),
        CategoryNames("Pulmonary Hypertension Medicines"),
        CategoryNames("Antilipedemic Agents"),
        CategoryNames("Antiplatelate"),
        CategoryNames("Thrombolytic Agents")


    )
    var respiratoryMedicines: MutableList<CategoryNames> = mutableListOf(

        CategoryNames(" Antitussive/Expectorant"),
        CategoryNames(" Anti-asthmatic and COPD Medicines"),
        CategoryNames("Allergic Rhinitis Medicines"),
        CategoryNames("Sarcoidosis Medicines"),
        CategoryNames("Apnea of prematurity Medicines")


    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category_names)


        recyclerView = findViewById(R.id.sub_category_name_list_recycle_view)
        adapter = SubCategoryNamesAdapter(gastroIntestinalMedicines)
        layout = LinearLayoutManager(this)


        categoryTittle = findViewById(R.id.sub_category_name_tittle)
        categoryTittle.text = intent.getStringExtra("categoryClicked")


        recyclerView.adapter = adapter
        recyclerView.layoutManager = layout


    }
}