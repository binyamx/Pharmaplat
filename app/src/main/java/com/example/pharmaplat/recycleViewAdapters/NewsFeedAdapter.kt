package com.example.pharmaplat.recycleViewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.NewsFeed
import com.example.pharmaplat.R



class NewsFeedAdapter (private var newsFeedList: MutableList<NewsFeed>)
    : RecyclerView.Adapter<NewsFeedAdapter.NewsFeedViewHolder>(){

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): NewsFeedViewHolder {
            val context = parent.context
            val inflater = LayoutInflater.from(context)
            val shouldAttachToParentImmediately = false
            val view = inflater.inflate(R.layout.item_news_feed,parent, shouldAttachToParentImmediately )
            return NewsFeedViewHolder(view)
        }

        override fun onBindViewHolder(holder: NewsFeedViewHolder, position: Int) {
            val item = newsFeedList[position]
            holder.bind(item)
        }

        override fun getItemCount(): Int = newsFeedList.size

        class NewsFeedViewHolder(v: View) : RecyclerView.ViewHolder(v){

            private  var view: View = v
            private lateinit var  newsFeedList: NewsFeed
            private var messageImageView: ImageView = v.findViewById(R.id.message_image_view)
            private var userImageView: ImageView = v.findViewById(R.id.user_circle_image_view)
            private var messageText: TextView = v.findViewById(R.id.message_text_view)
            private var userName: TextView = v.findViewById(R.id.user_name_text_view)
            private var commentButton: Button = v.findViewById(R.id.general_comment_button)

            fun bind (newsFeedList: NewsFeed){
                this.newsFeedList = newsFeedList
                userName.text = newsFeedList.userName
                userImageView.setImageResource(newsFeedList.userImage)
                messageText.text = newsFeedList.message
                messageImageView.setImageResource(newsFeedList.messageImage)


                val commentCount: Int = newsFeedList.comment
                if (commentCount > 0){
                    commentButton.text = view.resources.getQuantityString(R.plurals.comment_with_counts,
                        commentCount, commentCount)
                } else{
                    commentButton.text = view.resources.getString(R.string.comment_with_zero_counts)
                }





//
            }

        }
}