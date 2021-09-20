package com.example.pharmaplat.recycleViewAdapters.categoryAdapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.CategoryNames
import com.example.pharmaplat.ItemCatalog.SubSubCategory
import com.example.pharmaplat.R

class SubCategoryNamesAdapter(private var subCategoryNamesList: MutableList<CategoryNames>) :
    RecyclerView.Adapter<SubCategoryNamesAdapter.SubCategoryNamesViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubCategoryNamesViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false
        val view = inflater.inflate(
            R.layout.item_category_names,
            parent,
            shouldAttachToParentImmediately
        )

        return SubCategoryNamesViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: SubCategoryNamesViewHolder,
        position: Int
    ) {
        val item = subCategoryNamesList[position]
        holder.bindCategoryNamesList(item)
    }

    override fun getItemCount(): Int = subCategoryNamesList.size

    class SubCategoryNamesViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View
        private var name: TextView
        private lateinit var subCategoryNamesList: CategoryNames

        init {
            view = v
            name = v.findViewById(R.id.category_name_text_item)
            v.setOnClickListener(this)
        }

        fun bindCategoryNamesList(categoryNamesList: CategoryNames) {
            this.subCategoryNamesList = categoryNamesList
            name.text = categoryNamesList.name
        }


        override fun onClick(v: View?) {
            val context = itemView.context!!
            val intent = Intent(context, SubSubCategory::class.java)
            intent.putExtra("subCategoryClicked", subCategoryNamesList.name)
            context.startActivity(intent)
        }


    }
}