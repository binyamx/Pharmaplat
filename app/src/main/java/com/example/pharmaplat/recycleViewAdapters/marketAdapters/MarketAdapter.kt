package com.example.pharmaplat.recycleViewAdapters.marketAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.GeneralPostData
import com.example.pharmaplat.DataModel.MarketItem
import com.example.pharmaplat.R

class MarketAdapter(private var postList: MutableList<GeneralPostData>)
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
        private  var fullNameName: TextView
        private  var reviewRating: RatingBar
        private  var reviewAmount: TextView
        private  var postTittle: TextView
        private  var postDescription: TextView
        private  var userPicture: ImageView
        private  var postPicture: ImageView
        private lateinit var postList: GeneralPostData


        init {
            view = v
            fullNameName = v.findViewById(R.id.user_name_general_text)
            reviewRating = v.findViewById(R.id.general_post_profile_rating)
            reviewAmount = v.findViewById(R.id.general_post_profile_amount_of_raters)
            postTittle = v.findViewById(R.id.general_post_title_text_view)
            postDescription = v.findViewById(R.id.general_post_detail_text_view)
            userPicture = v.findViewById(R.id.general_user_circle_image_view)
            postPicture = v.findViewById(R.id.general_post_image_view)

        }


        fun bind (postList: GeneralPostData){
            this.postList = postList
            fullNameName.text = postList.fullName
            reviewRating.rating = postList.reviewRating!!
            reviewAmount.text = postList.reviewAmount.toString()
            postTittle.text = postList.postTitle
            postDescription.text = postList.postDetail
            postList.userPicture?.let { userPicture.setImageResource(it) }
            postList.postPicture?.let { postPicture.setImageResource(it) }

        }



    }


}