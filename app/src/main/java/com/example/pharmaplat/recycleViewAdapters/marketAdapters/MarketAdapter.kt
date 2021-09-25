package com.example.pharmaplat.recycleViewAdapters.marketAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pharmaplat.DataModel.BrowsableItemData
import com.example.pharmaplat.R

class MarketAdapter( var postList: MutableList<BrowsableItemData>)
    : RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarketViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false
        val view = inflater.inflate(R.layout.item_general_post,parent, shouldAttachToParentImmediately )
        return MarketViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        val item = postList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = postList.size

    class MarketViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View
        private  var userFullName: TextView
        private  var productTitle: TextView
        private  var productPicture: ImageView
        private lateinit var postList: BrowsableItemData

/*        private  var reviewRating: RatingBar
        private  var reviewAmount: TextView
        private  var postDescription: TextView
        private  var userPicture: ImageView*/



        init {
            view = v
            userFullName = v.findViewById(R.id.user_name_general_text)
            productTitle = v.findViewById(R.id.general_post_title_text_view)
            productPicture = v.findViewById(R.id.general_post_image_view)

/*            reviewRating = v.findViewById(R.id.general_post_profile_rating)
            reviewAmount = v.findViewById(R.id.general_post_profile_amount_of_raters)
              postDescription = v.findViewById(R.id.general_post_detail_text_view)
            userPicture = v.findViewById(R.id.general_user_circle_image_view)
          */

        }


        fun bind (postList: BrowsableItemData){
            this.postList = postList
            userFullName.text = postList.userFullName
            productTitle.text = postList.productTitle
            Glide.with(itemView.context)
                .load(postList.productPicture)
                .into(productPicture)

         

          /*  postDescription.text = postList.postDetail
            postList.userPicture?.let { userPicture.setImageResource(it) }
            reviewRating.rating = postList.reviewRating!!
            reviewAmount.text = postList.reviewAmount.toString()*/


        }



    }


}