package com.example.pharmaplat.recycleViewAdapters.categoryAdapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.CategoryNames
import com.example.pharmaplat.itemCatalog.ProductsInFinalCategory
import com.example.pharmaplat.R

class SubSubCategoryNamesAdapter(private var subSubCategoryNamesList: MutableList<CategoryNames>) :
    RecyclerView.Adapter<SubSubCategoryNamesAdapter.SubSubCategoryNamesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubSubCategoryNamesViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false
        val view = inflater.inflate(
            R.layout.item_category_names,
            parent,
            shouldAttachToParentImmediately
        )

        return SubSubCategoryNamesViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: SubSubCategoryNamesViewHolder,
        position: Int
    ) {
        val item = subSubCategoryNamesList[position]
        holder.bindCategoryNamesList(item)
    }

    override fun getItemCount(): Int = subSubCategoryNamesList.size

    class SubSubCategoryNamesViewHolder(v: View) : RecyclerView.ViewHolder(v),
        View.OnClickListener {

        private var view: View
        private var name: TextView
        private lateinit var subSubCategoryNamesList: CategoryNames

        init {
            view = v
            name = v.findViewById(R.id.category_name_text_item)
            v.setOnClickListener(this)
        }

        fun bindCategoryNamesList(subSubcategoryNamesList: CategoryNames) {
            this.subSubCategoryNamesList = subSubcategoryNamesList
            name.text = subSubcategoryNamesList.name
        }


        override fun onClick(v: View?) {
            val context = itemView.context!!

            val intent = Intent(context, ProductsInFinalCategory::class.java)
            intent.putExtra("subSubCategoryClicked", subSubCategoryNamesList.name)
            context.startActivity(intent)
        }


    }
}