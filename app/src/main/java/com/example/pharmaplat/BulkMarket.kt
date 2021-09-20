package com.example.pharmaplat

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.R
import com.example.pharmaplat.recycleViewAdapters.BulkAdapter

class BulkMarket : Fragment() {

    private lateinit var bulkRecyclerView: RecyclerView
    private lateinit var bulkAdapter: BulkAdapter
    private lateinit var bulkLayout: LinearLayoutManager

    var bulkList: MutableList<Int> = mutableListOf(
        R.drawable.fiction_one, R.drawable.fiction_two,
        R.drawable.fiction_three, R.drawable.fiction_four,
        R.drawable.fiction_five, R.drawable.fiction_six,
        R.drawable.fiction_seven, R.drawable.fiction_eight,
        R.drawable.fiction_nine, R.drawable.fiction_ten,
        R.drawable.fiction_one, R.drawable.fiction_two,
        R.drawable.fiction_three, R.drawable.fiction_four,
        R.drawable.fiction_five, R.drawable.fiction_six,
        R.drawable.fiction_seven, R.drawable.fiction_eight,
        R.drawable.fiction_nine, R.drawable.fiction_ten
    )

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bulk_market, container, false)
        bulkRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_bulk)
        bulkAdapter = BulkAdapter(bulkList)
        bulkLayout = LinearLayoutManager(context!!)
        bulkRecyclerView.adapter = bulkAdapter
        bulkRecyclerView.layoutManager = bulkLayout
        return view
    }
}