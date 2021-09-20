package com.example.pharmaplat.recycleViewAdapters.categoryAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.CellClickListener
import com.example.pharmaplat.DataModel.CategoryNames
import com.example.pharmaplat.DataModel.SubCategoryData
import com.example.pharmaplat.databinding.ItemSubCategoryReyclerviewBinding

class CategoryNamesDynamicAdapter(
    private var subCategoryData: MutableList<SubCategoryData>,
    private val screenName: String,
    ) :
    RecyclerView.Adapter<CategoryNamesDynamicAdapter.CategoryNamesDynamicViewHolder>(), CellClickListener{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryNamesDynamicViewHolder {
        val binding = ItemSubCategoryReyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return CategoryNamesDynamicViewHolder(binding)


    }

    override fun onBindViewHolder(
        holder: CategoryNamesDynamicViewHolder,
        position: Int
    ) {


        holder.viewDataBinding.subCategoryNameTextItem.text = subCategoryData[position].subCategoryTittle
        holder.viewDataBinding.finalCategoryRecyclerView.apply {
            adapter = FinalCategoryAdapter(
                subCategoryData[position].finalCategoryList,
                this@CategoryNamesDynamicAdapter,
                screenName
            )
            setOnClickListener {  }
        }


    }

    override fun getItemCount(): Int = subCategoryData.size

    class CategoryNamesDynamicViewHolder(val viewDataBinding: ItemSubCategoryReyclerviewBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    override fun OnClickListener(cell: CategoryNames) {
        TODO("Not yet implemented")
    }

}