package com.example.pharmaplat.main_screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.NewsFeed
import com.example.pharmaplat.R
import com.example.pharmaplat.recycleViewAdapters.NewsFeedAdapter


class NewsFeed : Fragment() {

    companion object {
        fun newInstance(): com.example.pharmaplat.main_screen.NewsFeed {
            return NewsFeed()
        }
    }

    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var newsadapter: NewsFeedAdapter
    private lateinit var newslayout: LinearLayoutManager


    var newsFeedList: MutableList<NewsFeed> = mutableListOf(
        NewsFeed(
            "Hanicho", R.drawable.hanicho,
            R.drawable.fiction_three, "Where can I get this ??", 0
        ),
        NewsFeed(
            "Shemsu Kahn", R.drawable.shemsu,
            R.drawable.fiction_seven, "we have Analogue X- Ray.", 7
        ),
        NewsFeed(
            "JUNPENG TEXTILE MACHINERY ", R.drawable.fiction_ten,
            R.drawable.fiction_ten, "\uF076 Medical Surgical Face Mask\n" +
                    "\uF076 Price is very attractive.  Phone 0911119016/0909612693.\n" +
                    "    Order now and we will deliver right away", 3
        ),
        NewsFeed(
            "Shewandagn Mekonen", R.drawable.shewandagn,
            R.drawable.fiction_one, "Flecainide 14mg is needed : inbox me if you have\n" +
                    "Flecainide ያለው ሰው በውስጥ መስመር መልእክት አስቀምጡልኝ።", 5
        ),
        NewsFeed(
            "Ebsa Tolla", R.drawable.aychal,
            R.drawable.fiction_four, "Where can i get this pls..?", 9
        ),
        NewsFeed(
            "Misikir D", R.drawable.zeritu,
            R.drawable.fiction_two, "Very urgent contact me on \n" +
                    "0924071166 gemechu", 2
        ),
        NewsFeed(
            "Jonah", R.drawable.jonah,
            R.drawable.fiction_nine, "Finger tip pulse oximetry\n" +
                    "With battery\n" +
                    "available @@ stock \n" +
                    "1200 ETB\n" +
                    "Call 0913517551/0934910861", 0
        ),
        NewsFeed(
            "Abate Dereje", R.drawable.abate,
            R.drawable.fiction_eight, "New Arrival ICU Ventilator is Available,\n" +
                    "for your inquiry please call to 0938 838383 Abate", 9
        ),
        NewsFeed(
            "Rediet Zewdu", R.drawable.rediet,
            R.drawable.fiction_six, "Remdesivir 100mg powder for injection", 2
        ),
        NewsFeed(
            "Meron Ahmed", R.drawable.koka,
            R.drawable.fiction_five, "NEW ARIVAL \n" +
                    "CEFTAZIDIME 1GM INJECTION \n" +
                    "- WITH STERILE WATER FIR INJECTION", 1
        ),


        )


    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_news_feed, container, false)



        newsRecyclerView = view.findViewById(R.id.newsFeed_recycler_view)
        newsadapter = NewsFeedAdapter(this.newsFeedList)
        newslayout = LinearLayoutManager(context!!)

        newsRecyclerView.layoutManager = newslayout
        newsRecyclerView.adapter = newsadapter


        return view
    }


}