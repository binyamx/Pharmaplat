package com.example.pharmaplat.recycleViewAdapters.categoryAdapters

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.provider.Settings.Global.getString
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.CellClickListener
import com.example.pharmaplat.DataModel.CategoryNames
import com.example.pharmaplat.ItemCatalog.ListForOneItem
import com.example.pharmaplat.LoadFinalRecycler
import com.example.pharmaplat.R
import com.example.pharmaplat.databinding.ItemCategoryNamesBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.dialog_create_post.*
import kotlinx.android.synthetic.main.item_recycler_market.*
import java.util.*

class FinalCategoryAdapter(
    private val finalCategoryList: MutableList<CategoryNames>,
    private val cellClickListener: CellClickListener,
    private val screenName: String,
)
    :RecyclerView.Adapter<FinalCategoryAdapter.FinalCategoryViewHolder>() {

    private val TAG = "FinalCategoryAdapter"

    private lateinit var  loadFinalRecycler: LoadFinalRecycler
    private lateinit var createPostDialog: Dialog
    private lateinit var context: Context

    // Firebase
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()




    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FinalCategoryViewHolder {
        val binding = ItemCategoryNamesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FinalCategoryViewHolder(binding)

    }

    override fun onBindViewHolder(
        holder: FinalCategoryViewHolder,
        position: Int
    ) {
        val categoryName = finalCategoryList[position].name
       holder.viewDataBinding.categoryNameTextItem.text = finalCategoryList[position].name
       holder.itemView.setOnClickListener {

           if (screenName == "Medicines") {
               val intent = Intent(holder.itemView.context, ListForOneItem::class.java)
               intent.putExtra("screenName",screenName)
               holder.itemView.context.startActivity(intent)
           } else {
               createPost(categoryName.toString())
           }

       }
    }

    private fun createPost(categoryName: String) {
        createPostDialog = Dialog(context)
        createPostDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        createPostDialog.setContentView(R.layout.dialog_create_post)
        createPostDialog.setCanceledOnTouchOutside(false)
        createPostDialog.category_name.text = context.getString(R.string.category_name
            ,categoryName.toString()
                .toLowerCase()
                .capitalize(Locale.ROOT)
        )
        createPostDialog.show()

        createPostDialog.create_post_button.setOnClickListener {
            val postTitle = createPostDialog.post_title.editText?.text.toString()
            val postDetail = createPostDialog.post_description.editText?.text.toString()

                    addToFireStore(postTitle, postDetail, categoryName.toString())
        }

        createPostDialog.cancel_button.setOnClickListener {
            createPostDialog.hide()
        }
    }

    private fun addToFireStore(postTitle: String, postDetail: String, categoryName: String) {

        val searchKeyWords = generateSearchKeyWords(postTitle)

        val postMap = hashMapOf<String, Any>(
            "categoryName" to categoryName,
            "postTitle" to postTitle,
            "postDetail" to postDetail,
            "searchKeyWords" to searchKeyWords,
        )

        firestore.collection("Posts")
            .add(postMap)
            .addOnSuccessListener {

                Log.d(TAG, postMap.toString())
                createPostDialog.hide()
                
            }.addOnFailureListener {
                Log.d(TAG, it.toString())
                Toast.makeText(context, "Posting Failed. Try Again.", Toast.LENGTH_SHORT).show()
            }


    }

    private fun generateSearchKeyWords(postTitle: String): List<String> {
        var postTitleLowerCase = postTitle.toLowerCase(Locale.ROOT)
        var keyWords = mutableListOf<String>()

        // Split all words from the string
        val words = postTitleLowerCase.split(" ")

        // For each word
        for (word in words){
           var appendString = ""

            // For every character in the whole string

            for (charPosition in postTitleLowerCase.indices){
                appendString += postTitleLowerCase[charPosition].toString()
                keyWords.add(appendString)
            }
            // remove first word from the string

            postTitleLowerCase = postTitleLowerCase.replace("$word ", "")
        }
        return  keyWords
    }

    override fun getItemCount(): Int = finalCategoryList.size

    class FinalCategoryViewHolder (val viewDataBinding: ItemCategoryNamesBinding)
        :RecyclerView.ViewHolder(viewDataBinding.root)


}