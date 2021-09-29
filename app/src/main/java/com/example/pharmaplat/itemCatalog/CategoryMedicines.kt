package com.example.pharmaplat.itemCatalog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.CellClickListener
import com.example.pharmaplat.DataModel.CategoryNames
import com.example.pharmaplat.DataModel.SubCategoryData
import com.example.pharmaplat.UpdateRecyclerView
import com.example.pharmaplat.databinding.ActivityCategoryMedicinesBinding
import com.example.pharmaplat.recycleViewAdapters.categoryAdapters.CategoryNamesDynamicAdapter
import com.example.pharmaplat.recycleViewAdapters.categoryAdapters.MainCategoryNamesStaticAdapter
import com.example.pharmaplat.search.Search

class CategoryMedicines : AppCompatActivity(), UpdateRecyclerView {

    private lateinit var binding: ActivityCategoryMedicinesBinding

    // Screen name
    private lateinit var screenName: String

    private lateinit var mainCategoryRecycleView: RecyclerView
    private lateinit var mainCategoryAdapter: MainCategoryNamesStaticAdapter
    private lateinit var mainCategoryLayout: LinearLayoutManager

    private lateinit var subCategoryRecyclerView: RecyclerView
    private lateinit var subCategoryAdapter: CategoryNamesDynamicAdapter
    private lateinit var subCategoryLayout: LinearLayoutManager


    private val medicinesCategoryList: MutableList<CategoryNames> = mutableListOf(

        CategoryNames("Gastrointestinal Medicines"),
        CategoryNames("Cardiovascular Medicines"),
        CategoryNames("Respiratory Medicines"),
        CategoryNames("Antibacterials"),
        CategoryNames("Antifungals"),
        CategoryNames("Antivirals"),
        CategoryNames("Antiprotozoals"),
        CategoryNames("Antihelmentics"),
        CategoryNames("Central Nervous System Medicines"),
        CategoryNames("Anesthesia Medicines"),
        CategoryNames("Musculoskeletal and Joint Disease Medicines"),
        CategoryNames("Vitamins"),
        CategoryNames("Antihistamines"),
        CategoryNames("Endocrine Disorder Medicines"),
        CategoryNames("Contraceptives"),
        CategoryNames("Gynecological and Obstetrics Medications"),
        CategoryNames("Blood Products and Medicines"),
//        CategoryNames("Fluid, Electrolyte and Acid Base Balance Medicines"),
//        CategoryNames("Immunomodulators"),
//        CategoryNames("Antineoplastics and Supportive Medicines"),
//        CategoryNames("Benign Prostate Hyperplasia Medicines"),
//        CategoryNames("Ophthalmic Agents"),
//        CategoryNames("Ear, Nose and Throat Preparations"),
//        CategoryNames("Dermatological Agents"),
//        CategoryNames("Vaccines"),
//        CategoryNames("Sera and Immunoglobulin"),
//        CategoryNames("Poisoning Antidotes and other Substances"),
//        CategoryNames("Radiocontrast Media"),
//        CategoryNames("Miscellaneous"),


    )

    private val antacids: MutableList<CategoryNames> = mutableListOf(

        CategoryNames("Aluminum Hydroxide + Magnesium Hydroxide + Simethicone"),
        CategoryNames("Aluminum Hydroxide + Magnesium Trisilicate"),


        )
    private val antiUlcer: MutableList<CategoryNames> = mutableListOf(

        CategoryNames("Cimetidine"),
        CategoryNames("Omeprazole"),
        CategoryNames("Ranitidine")

    )
    private val upperGIBleeding: MutableList<CategoryNames> = mutableListOf(

        CategoryNames("Octreotide"),
        CategoryNames("Pantoprazole"),
        CategoryNames("Omeprazole"),
        CategoryNames("Propranolol")

    )
    private val antiSpasmodics: MutableList<CategoryNames> = mutableListOf(

        CategoryNames("Hyoscine (Scopolamine) Butylbromide"),
        CategoryNames("Hyoscine (Scopolamine) Hydrobromide"),

        )
    private val antiemetics: MutableList<CategoryNames> = mutableListOf(

        CategoryNames("Chlorpromazine Hydrochloride"),
        CategoryNames("Dimenhydrinate"),
        CategoryNames("Dexamethasone"),
        CategoryNames("Meclizine Hydrochloride + Vitamin B6"),
        CategoryNames("Metoclopramide Hydrochloride"),
        CategoryNames("Ondansetron")
    )
    private val catharticsAndLaxaties: MutableList<CategoryNames> = mutableListOf(

        CategoryNames("Bisacodyl"),
        CategoryNames("Castor oil"),
        CategoryNames("Glycerin"),
        CategoryNames("Lactulose"),
        CategoryNames("Polyethylene Glycol (PEG)"),

        )
    private val diarrheaManagment: MutableList<CategoryNames> = mutableListOf(

        CategoryNames("Loperamide"),
        CategoryNames("Oral Rehydration Salt"),
        CategoryNames("Zinc Sulphate"),
        CategoryNames("ReSoMal"),
        CategoryNames("Polyethylene Glycol (PEG)"),

        )
    private val antiflatulents: MutableList<CategoryNames> = mutableListOf(
        CategoryNames("Simethicone")
    )
    private val antihaemorrhoidal: MutableList<CategoryNames> = mutableListOf(

        CategoryNames("Betamethasone Valerate + Phenylephrine HCl + LidocaineHCl"),
        CategoryNames("Hydrocortisone + Framycetin + Cinchocaine + Esculoside"),
    )
    private val iBS: MutableList<CategoryNames> = mutableListOf(

        CategoryNames("Sulfasalazine"),
        CategoryNames("Azathioprine"),
        CategoryNames("Methylprednisolone"),

        )

    private val subgastroIntestinalMedicines: MutableList<SubCategoryData> = mutableListOf(
        SubCategoryData("Antacids", antacids),
        SubCategoryData("Antiulcer agents", antiUlcer),
        SubCategoryData("Upper GI bleeding Medicines", upperGIBleeding),
        SubCategoryData("Antispasmodics/ Spasmolytics analgesics", antiSpasmodics),
        SubCategoryData("Antiemetics", antiemetics),
        SubCategoryData("Laxatives and Cathartics  ", catharticsAndLaxaties),
        SubCategoryData("Diarrhea management Medicines", diarrheaManagment),
        SubCategoryData("Antiflatulents", antiflatulents),
        SubCategoryData("Antihaemorrhoidal Agents", antihaemorrhoidal),
        SubCategoryData("Inflammatory Bowel Disease Medicines", iBS)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMedicinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        screenName = intent.getStringExtra("categoryTitle")!!
        // dynamic Recycler view for  sub categories

        val mainBinding = binding.categoryMembersRecycler
        mainBinding.apply {

            layoutManager = LinearLayoutManager(this@CategoryMedicines)
            adapter = CategoryNamesDynamicAdapter(
                subgastroIntestinalMedicines,
                screenName
            )
        }


        // Static Recycler view for  categories

        mainCategoryRecycleView = binding.mainCategoryTitleRecycler
        mainCategoryAdapter = MainCategoryNamesStaticAdapter(medicinesCategoryList, this)
        mainCategoryLayout = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mainCategoryRecycleView.adapter = mainCategoryAdapter
        mainCategoryRecycleView.layoutManager = mainCategoryLayout


        // Click on search place moves the screen to search screen

        binding.searchEditText.setOnClickListener {
            val intetnt = Intent(this, Search::class.java)
            startActivity(intetnt)
        }
    }


//    Call back from  category name Static Adapter bind view holder on click listener.
       override fun callback(
        position: Int,
        item: MutableList<SubCategoryData>
    ) {

        binding.categoryTitle.text = intent.getStringExtra("categoryTitle")
        subCategoryRecyclerView = binding.categoryMembersRecycler
        subCategoryAdapter = CategoryNamesDynamicAdapter(item, screenName)
        subCategoryLayout = LinearLayoutManager(this)
        subCategoryAdapter.notifyDataSetChanged()
        subCategoryRecyclerView.adapter = subCategoryAdapter
        subCategoryRecyclerView.layoutManager = subCategoryLayout


    }

//    override fun OnClickListener(cell: CategoryNames) {
//        val value = intent.getStringExtra("categoryTitle")
//
//        if (value == "Medicines") {
//            val intent = Intent(this, ListForOneItem::class.java)
//            intent.putExtra("subSubCategoryClicked", cell.name.toString())
//            startActivity(intent)
//        } else {
//            val intent = Intent(this, ListForOneItem::class.java)
//            intent.putExtra("subSubCategoryClicked", "${cell.name.toString()} addPost")
//            startActivity(intent)
//        }
//
//    }


}