package com.example.pharmaplat.recycleViewAdapters.marketAdapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.ItemsAndPostAmount
import com.example.pharmaplat.ItemCatalog.ListForOneItem
import com.example.pharmaplat.R

class CategoriesAndContentAmountAdapter(private var drugList: MutableList<ItemsAndPostAmount>) :
    RecyclerView.Adapter<CategoriesAndContentAmountAdapter.DrugViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DrugViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false

        val view = inflater.inflate(
            R.layout.item_drugs_in_a_catagory,
            parent,
            shouldAttachToParentImmediately
        )
        return DrugViewHolder(view)
    }

    override fun onBindViewHolder(holder: DrugViewHolder, position: Int) {
        val item = drugList[position]
        holder.bindDrugList(item)
    }

    override fun getItemCount(): Int = drugList.size

    class DrugViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View
        private lateinit var drugList: ItemsAndPostAmount
        private var name: TextView
        private var postAmount: TextView

        override fun onClick(v: View?) {
            val context = itemView.context!!
            val intent = Intent(context, ListForOneItem::class.java)
            intent.putExtra("ClickedItemName", drugList.name)
            context.startActivity(intent)
        }


        init {
            view = v
            name = v.findViewById(R.id.drug_name_text_view)
            postAmount = v.findViewById(R.id.post_amount)
            v.setOnClickListener(this)
        }

        fun bindDrugList(drugList: ItemsAndPostAmount) {
            this.drugList = drugList
            name.text = drugList.name
            postAmount.text = drugList.PostAmount
        }


    }


}