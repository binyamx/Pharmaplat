package com.example.pharmaplat.main_screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.Demand
import com.example.pharmaplat.DataModel.HotDeals
import com.example.pharmaplat.DataModel.MostViewed
import com.example.pharmaplat.ItemCatalog.CategoryNameList
import com.example.pharmaplat.R
import com.example.pharmaplat.recycleViewAdapters.marketAdapters.HotDealsAdapter
import com.example.pharmaplat.recycleViewAdapters.marketAdapters.MostViewedAdapter
import kotlinx.android.synthetic.main.fragment_demand.view.*

class DemandFragment : Fragment() {

    companion object{
        fun newInstance():DemandFragment {
            return DemandFragment()
        }
    }

    private var demandList: ArrayList<Demand> = ArrayList()
//    private lateinit var linearLayoutManager: LinearLayoutManager
//    private lateinit var  demandAdapter: DemandAdapter
//    private lateinit var demandRecyclerView: RecyclerView

    private var mostRequestedList: MutableList<MostViewed> = mutableListOf(
        MostViewed(
            "Abel girma",
            4.0F,
            "Where can i get this?",
            R.drawable.fiction_five
        ),
        MostViewed("Gonder Pharmacy",
            4.2F,
            "We need this in bulk.",
            R.drawable.sanitizer),
        MostViewed(
            "Harer Medical Center",
            4.3F,
            "We need three ventilators",

            R.drawable.fiction_eight
        )

    )

    private var recentRequestsList: MutableList<HotDeals> = mutableListOf(
        HotDeals("Africa Pharmacy", "I need this in bulk ASAP!", R.drawable.fiction_one),
        HotDeals("Gelila Pharmacy", "We want 56 vials.", R.drawable.fiction_two),
        HotDeals("Sihin Pharmacy", "If you have this contact me.", R.drawable.fiction_three)

    )

    private lateinit var mostRequestedReyclerView: RecyclerView
    private lateinit var mostRequestedAdapter: MostViewedAdapter
    private lateinit var mostRequestedLayoutManager: LinearLayoutManager


    private lateinit var recentRequestsRecyclerView: RecyclerView
    private lateinit var recentRequestsAdapter: HotDealsAdapter
    private lateinit var recentRequestsLayoutManager: LinearLayoutManager




    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_demand, container, false)

        demandList.add( Demand("Meropenem", 37,9, 28))
        demandList.add( Demand("Doxorubucin", 76,23, 53))
        demandList.add( Demand("Amphotercin", 53,11, 42))
        demandList.add( Demand("Cefepem", 93,24, 69))
        demandList.add( Demand("Carbamazepime", 28,9, 19))
        demandList.add( Demand("Enoxaparin", 58,22, 36))

//        demandRecyclerView = view.findViewById(R.id.demand_recycler_view)
//
//        linearLayoutManager = LinearLayoutManager(context!!)
//        demandAdapter = DemandAdapter(this.demandList)
//
//        demandRecyclerView.layoutManager = linearLayoutManager
//        demandRecyclerView.adapter = demandAdapter



        view.medicines_demand_linear_layout.setOnClickListener {
            val intent = Intent(this.context, CategoryNameList::class.java)
            intent.putExtra("categoryTitle", "Medicines")
            startActivity(intent)
        }

//        view.vacancies_demand_linear_layout.setOnClickListener {
//            val intent = Intent(this.context, CategoryNameList::class.java)
//            intent.putExtra("categoryTitle", "Vacancies")
//            startActivity(intent)
//        }

        view.instruments_demand_linear_layout.setOnClickListener {
            val intent = Intent(this.context, CategoryNameList::class.java)
            intent.putExtra("categoryTitle", "Instruments")
            startActivity(intent)
        }

//        view.services_demand_linear_layout.setOnClickListener {
//            val intent = Intent(this.context, CategoryNameList::class.java)
//            intent.putExtra("categoryTitle", "Services")
//            startActivity(intent)
//        }


        // Most Requested Recycler
        mostRequestedReyclerView = view.findViewById(R.id.most_requested_recycler_view)
        mostRequestedReyclerView.setHasFixedSize(true)
        mostRequestedAdapter = MostViewedAdapter(mostRequestedList)
        mostRequestedLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mostRequestedReyclerView.layoutManager = mostRequestedLayoutManager
        mostRequestedReyclerView.adapter = mostRequestedAdapter


        //Recent requests Recycler
        recentRequestsRecyclerView = view.findViewById(R.id.recent_requests_recycler_view)
        recentRequestsRecyclerView.setHasFixedSize(true)
        recentRequestsAdapter = HotDealsAdapter(recentRequestsList)
        recentRequestsLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recentRequestsRecyclerView.layoutManager = recentRequestsLayoutManager
        recentRequestsRecyclerView.adapter = recentRequestsAdapter






        return view
    }


}