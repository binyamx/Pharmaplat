package com.example.pharmaplat.recycleViewAdapters.marketAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.MostViewed
import com.example.pharmaplat.R

class MostViewedAdapter(private var mostViewedList: MutableList<MostViewed>)
    : RecyclerView.Adapter<MostViewedAdapter.MostViewedViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MostViewedAdapter.MostViewedViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParrentImmediately = false
        val view = inflater.inflate(R.layout.item_most_viewd_card_design, parent, shouldAttachToParrentImmediately)
        return MostViewedViewHolder(view)
    }

    override fun onBindViewHolder(holder: MostViewedAdapter.MostViewedViewHolder, position: Int) {
        val item = mostViewedList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = mostViewedList.size


    class MostViewedViewHolder(v: View): RecyclerView.ViewHolder(v){

        private var view: View
        private var tittle: TextView
        private var ratingBar: RatingBar
        private var descrition: TextView
        private var picture: ImageView
        private lateinit var mostViewedList: MostViewed

        init {
            view = v
            tittle = v.findViewById(R.id.most_viewed_tittle_text)
            ratingBar = v.findViewById(R.id.most_viewed_rating_bar)
            descrition = v.findViewById(R.id.most_viewed_description_text)
            picture = v.findViewById(R.id.most_viewed_imageView)

        }

        fun bind ( mostViewedList: MostViewed){
            this.mostViewedList = mostViewedList
            tittle.text = mostViewedList.tittle
            ratingBar.rating = mostViewedList.rating!!
            descrition.text = mostViewedList.description
            picture.setImageResource(mostViewedList.picture!!)
        }



    }
}