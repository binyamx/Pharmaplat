package com.example.pharmaplat.recycleViewAdapters.marketAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.HotDeals
import com.example.pharmaplat.R

class HotDealsAdapter(private var hotDealsList: MutableList<HotDeals>) :
    RecyclerView.Adapter<HotDealsAdapter.HotDealsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HotDealsAdapter.HotDealsViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParrentImmediately = false
        val view = inflater.inflate(
            R.layout.item_hot_deals_recycler,
            parent,
            shouldAttachToParrentImmediately
        )
        return HotDealsViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotDealsAdapter.HotDealsViewHolder, position: Int) {
        val item = hotDealsList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = hotDealsList.size

    class HotDealsViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View
        private var tittle: TextView
        private var descrition: TextView
        private var picture: ImageView
        private lateinit var hotDealsList: HotDeals

        init {
            view = v
            tittle = v.findViewById(R.id.hot_deals_tittle)
            descrition = v.findViewById(R.id.hot_deals_description)
            picture = v.findViewById(R.id.hot_deals_image)

        }

        fun bind(hotDealsList: HotDeals) {
            this.hotDealsList = hotDealsList
            tittle.text = hotDealsList.tittle
            descrition.text = hotDealsList.description
            picture.setImageResource(hotDealsList.picture!!)
        }

    }
}