package com.example.pharmaplat.recycleViewAdapters.categoryAdapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.CategoryNames
import com.example.pharmaplat.itemCatalog.ListForOneItem
import com.example.pharmaplat.createPost.CreatePost
import com.example.pharmaplat.databinding.ItemCategoryNamesBinding

class FinalCategoryAdapter(
    private val finalCategoryList: MutableList<CategoryNames>,
    private val screenName: String,
)
    :RecyclerView.Adapter<FinalCategoryAdapter.FinalCategoryViewHolder>() {



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FinalCategoryViewHolder {
        val binding = ItemCategoryNamesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FinalCategoryViewHolder(binding)

    }

    override fun onBindViewHolder(
        holder: FinalCategoryViewHolder,
        position: Int
    ) {
        val categoryName = finalCategoryList[position].name.toString()
       holder.viewDataBinding.categoryNameTextItem.text = finalCategoryList[position].name
       holder.itemView.setOnClickListener {

           if (screenName == "Medicines") {
               val intent = Intent(holder.itemView.context, ListForOneItem::class.java)
               intent.putExtra("categoryName",categoryName)
               holder.itemView.context.startActivity(intent)
           } else {
               val intent = Intent(holder.itemView.context, CreatePost::class.java)
               intent.putExtra("categoryName",categoryName)
               holder.itemView.context.startActivity(intent)

           }

       }
    }



    override fun getItemCount(): Int = finalCategoryList.size

    class FinalCategoryViewHolder (val viewDataBinding: ItemCategoryNamesBinding)
        :RecyclerView.ViewHolder(viewDataBinding.root)


}