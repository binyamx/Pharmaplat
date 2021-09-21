package com.example.pharmaplat.recycleViewAdapters.searchAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.SearchData
import com.example.pharmaplat.DataModel.SearchResultDataModel
import com.example.pharmaplat.R

class SearchAdapter (private var searchResultList: MutableList<SearchResultDataModel>)
    :RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false
        val view = inflater.inflate(R.layout.item_search,parent, shouldAttachToParentImmediately )
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
       val item = searchResultList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = searchResultList.size

    class SearchViewHolder (v: View) :RecyclerView.ViewHolder(v){


        private lateinit var searchResultList: SearchResultDataModel

        private var userFullName: TextView = v.findViewById(R.id.user_full_name)
        private var productPicture: ImageView = v.findViewById(R.id.product_image_view)
        private var productTitle: TextView = v.findViewById(R.id.product_title_text_view)

        fun bind (searchResultList: SearchResultDataModel){
            this.searchResultList = searchResultList
            userFullName.text = searchResultList.fullName
            productPicture.setImageBitmap(searchResultList.productPictureUri)
            productTitle.text = searchResultList.productTitle
        }
    }
}