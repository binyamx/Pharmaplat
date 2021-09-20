package com.example.pharmaplat.recycleViewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.R

class BulkAdapter (private var campaignList: MutableList<Int>)
    : RecyclerView.Adapter<BulkAdapter.CampaignViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CampaignViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false
        val view = inflater.inflate(R.layout.item_recycker_bulk, parent, shouldAttachToParentImmediately)
        return CampaignViewHolder(view)

    }

    override fun onBindViewHolder(holder: CampaignViewHolder, position: Int) {
        val item = campaignList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = campaignList.size

    class CampaignViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private  var view : View = v
        private var imageView : ImageView = view.findViewById(R.id.bulk_item_image_view)

        fun bind(image: Int){
            imageView.setImageResource(image)
        }

    }

}