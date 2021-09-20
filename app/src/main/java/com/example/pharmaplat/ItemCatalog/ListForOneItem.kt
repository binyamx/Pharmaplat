package com.example.pharmaplat.ItemCatalog

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.GeneralPostData
import com.example.pharmaplat.R
import com.example.pharmaplat.databinding.ActivityListForOneItemBinding
import com.example.pharmaplat.recycleViewAdapters.marketAdapters.MarketAdapter

class ListForOneItem : AppCompatActivity() {

    private val TAG = "ListForOneItem"

    private lateinit var binding : ActivityListForOneItemBinding

    private lateinit var screenName: String


    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: MarketAdapter

    private val marketList: MutableList<GeneralPostData> = mutableListOf(
        GeneralPostData("Meron Ahmed",4.7F,280,
            "CEFTAZIDIME",
            "NEW ARIVAL CEFTAZIDIME 1GM INJECTION - WITH STERILE WATER FIR INJECTION",
            R.drawable.rediet,
            R.drawable.fiction_five,),
        GeneralPostData("Jonah",4.7F,280,
            "Finger tip pulse oximetry",
            " With battery available @@ stock1200 ETB Call 0913517551/0934910861",
            R.drawable.jonah,
            R.drawable.fiction_nine),
        GeneralPostData("JUNPENG TEXTILE MACHINERY ",4.1F,320,
            "Medical Surgical Face Mask.",
            "Price is very attractive.Phone 0911119016/0909612693.Order now and we will deliver right away",
            R.drawable.koka,
            R.drawable.fiction_ten,),
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListForOneItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set tittle TextView with screenName
        screenName = intent.getStringExtra("screenName")!!
        Log.d(TAG, screenName)
        binding.titleTextView.text = screenName

        // Recycler view
        recyclerView = findViewById(R.id.listForOneItemRecycleView)
        layoutManager = LinearLayoutManager(this)
        adapter = MarketAdapter(marketList)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}