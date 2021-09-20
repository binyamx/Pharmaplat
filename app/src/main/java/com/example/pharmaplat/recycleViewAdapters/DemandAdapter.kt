package com.example.pharmaplat.recycleViewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.Demand
import com.example.pharmaplat.R

class DemandAdapter (private var demandList: MutableList<Demand>)
    : RecyclerView.Adapter<DemandAdapter.DemandViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemandViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false

        val view = inflater.inflate(R.layout.item_recycler_shortage, parent, shouldAttachToParentImmediately)
        return DemandViewHolder(view)
    }

    override fun onBindViewHolder(holder: DemandViewHolder, position: Int) {
        val item = demandList[position]
        holder.bindDemand(item)
    }

    override fun getItemCount(): Int = demandList.size

    class DemandViewHolder(v: View) :RecyclerView.ViewHolder(v), View.OnClickListener {

        private  var view : View
        private lateinit var demand : Demand
        private var name : TextView
        private var amountRequested: TextView
        private var aliveRequest: TextView
        private var expiredRequest: TextView

        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }

        init {
            view = v
            name = view.findViewById<TextView>(R.id.shortage_item_name_text_view)
            amountRequested = view.findViewById(R.id.request_quantity_text_view)
            aliveRequest = view.findViewById(R.id.alive_request_text_view)
            expiredRequest = view.findViewById(R.id.expired_request_text_view)
            v.setOnClickListener(this)
        }
        fun bindDemand(demand: Demand){
            this.demand = demand
            name.text = demand.name
            amountRequested.text = demand.amountRequested.toString()
            aliveRequest.text = demand.aliveRequest.toString()
            expiredRequest.text = demand.expiredRequest.toString()

        }


    }

    }