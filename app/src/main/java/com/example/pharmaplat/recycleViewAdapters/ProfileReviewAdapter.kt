package com.example.pharmaplat.recycleViewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.ReviewsData
import com.example.pharmaplat.R
import de.hdodenhof.circleimageview.CircleImageView

class ProfileReviewAdapter(private var reviewsList: MutableList<ReviewsData>) :
    RecyclerView.Adapter<ProfileReviewAdapter.ProfileReviewViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileReviewViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false
        val view = inflater.inflate(
            R.layout.item_profile_reviews,
            parent,
            shouldAttachToParentImmediately
        )

        return ProfileReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileReviewViewHolder, position: Int) {
        val item = reviewsList[position]
        holder.bindReviewList(item)
    }

    override fun getItemCount(): Int = reviewsList.size

    class ProfileReviewViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View
        private lateinit var reviewList: ReviewsData
        private var fullName: TextView
        private var ratingBar: RatingBar
        private var reviewdDate: TextView
        private var reviewDetail: TextView
        private var reviewPicture: CircleImageView


        init {
            view = v
            fullName = v.findViewById(R.id.reviewer_name_text_view)
            ratingBar = v.findViewById(R.id.reviewer_given_rating)
            reviewdDate = v.findViewById(R.id.review_given_date)
            reviewDetail = v.findViewById(R.id.review_detail)
            reviewPicture = v.findViewById(R.id.reviewer_circle_image_view)

        }

        fun bindReviewList(reviewList: ReviewsData) {
            this.reviewList = reviewList
            fullName.text = reviewList.reviewerFullName
            ratingBar.rating = reviewList.reviewRating!!
            reviewdDate.text = reviewList.reviewDate
            reviewDetail.text = reviewList.reviewDetail
            reviewPicture.setImageResource(reviewList.reviewerPicture!!)

        }


    }


}