package com.example.pharmaplat.recycleViewAdapters.marketAdapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.CatagoryList
import com.example.pharmaplat.R
import com.example.pharmaplat.itemCatalog.ItemsInACategory

class CategoryListAdapter (private var categoryList: MutableList<CatagoryList>)
    :RecyclerView.Adapter<CategoryListAdapter.CatagoryViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CatagoryViewHolder {
       val context = parent.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false

        val view = inflater.inflate(R.layout.item_market_catagories, parent, shouldAttachToParentImmediately)
        return CatagoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatagoryViewHolder, position: Int) {
        val item = categoryList[position]
        holder.bindCategory(item)
    }

    override fun getItemCount(): Int = categoryList.size

    class CatagoryViewHolder (v: View) : RecyclerView.ViewHolder(v), View.OnClickListener{

        private  var view: View
        private lateinit var categoryList: CatagoryList
        private  var name: TextView
        private  var icon: ImageView

        init {
            view = v
            name = view.findViewById(R.id.catagory_item_name)
            icon = view.findViewById(R.id.catagory_item_pic)
            v.setOnClickListener(this)
        }

        fun bindCategory(categoryList: CatagoryList){
            this.categoryList = categoryList
            name.text = categoryList.name
            icon.setImageResource(categoryList.icon)

        }



        override fun onClick(v: View?) {
            val context = itemView.context!!
            val intent = Intent(context,  ItemsInACategory::class.java )
            intent.putExtra("nameOfClickedCategory", categoryList.name)
            context.startActivity(intent)
        }

    }
}