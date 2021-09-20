package com.example.pharmaplat.recycleViewAdapters.marketAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.Featured
import com.example.pharmaplat.R

class FeaturedAdapter (private var featuredList: MutableList<Featured>)
    :RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeaturedAdapter.FeaturedViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParrentImmediately = false
        val view = inflater.inflate(R.layout.item_featured_recycler, parent, shouldAttachToParrentImmediately)
        return FeaturedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeaturedAdapter.FeaturedViewHolder, position: Int) {
       val item = featuredList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = featuredList.size

    class FeaturedViewHolder(v: View): RecyclerView.ViewHolder(v) {

        private var view: View
        private var tittle: TextView
        private var ratingBar: RatingBar
        private var descrition: TextView
        private var picture: ImageView
        private lateinit var featuredList: Featured

        init {
            view = v
            tittle = v.findViewById(R.id.featured_title)
            ratingBar = v.findViewById(R.id.featured_rating)
            descrition = v.findViewById(R.id.featured_description)
            picture = v.findViewById(R.id.featured_imageView)

        }

        fun bind (featuredList:Featured){
            this.featuredList = featuredList
            tittle.text = featuredList.tittle
            ratingBar.rating = featuredList.rating!!
            descrition.text = featuredList.description
            picture.setImageResource(featuredList.picture!!)
        }




    }
}