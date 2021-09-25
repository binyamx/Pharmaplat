package com.example.pharmaplat.recycleViewAdapters.categoryAdapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.CategoryNames
import com.example.pharmaplat.itemCatalog.SubCategoryNames
import com.example.pharmaplat.itemCatalog.SubSubCategory
import com.example.pharmaplat.R

class CategoryNamesAdapter(private var categoryNamesList: MutableList<CategoryNames>) :
    RecyclerView.Adapter<CategoryNamesAdapter.CategoryNamesViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryNamesViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false
        val view = inflater.inflate(
            R.layout.item_category_names,
            parent,
            shouldAttachToParentImmediately
        )

        return CategoryNamesViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CategoryNamesViewHolder,
        position: Int
    ) {
        val item = categoryNamesList[position]
        holder.bindCategoryNamesList(item)
    }

    override fun getItemCount(): Int = categoryNamesList.size

    class CategoryNamesViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View
        private var name: TextView
        private lateinit var categoryNamesList: CategoryNames

        init {
            view = v
            name = v.findViewById(R.id.category_name_text_item)
            v.setOnClickListener(this)
        }

        fun bindCategoryNamesList(categoryNamesList: CategoryNames) {
            this.categoryNamesList = categoryNamesList
            name.text = categoryNamesList.name
        }


        override fun onClick(v: View?) {
            val context = itemView.context!!


            // Here we send sub-categoryless catagory-names directly to subSubCategory

            if (categoryNamesList.name == "Gynecological and Obstetrics Medications" ||
                categoryNamesList.name == "Immunomodulators" ||
                categoryNamesList.name == "Benign Prostate Hyperplasia Medicines" ||
                categoryNamesList.name == "Ear, Nose and Throat Preparations" ||
                categoryNamesList.name == "Vaccines" ||
                categoryNamesList.name == "Sera and Immunoglobulin" ||
                categoryNamesList.name == "Poisoning Antidotes and other Substances" ||
                categoryNamesList.name == "Radiocontrast Media" ||
                categoryNamesList.name == "Miscellaneous"
            ) {

                val intent = Intent(context, SubSubCategory::class.java)
                intent.putExtra("subCategoryClicked", categoryNamesList.name)
                context.startActivity(intent)

            } else {

                val intent = Intent(context, SubCategoryNames::class.java)
                intent.putExtra("categoryClicked", categoryNamesList.name)
                context.startActivity(intent)

            }


        }


    }

}