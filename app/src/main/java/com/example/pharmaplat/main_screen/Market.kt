package com.example.pharmaplat.main_screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmaplat.DataModel.*
import com.example.pharmaplat.R
import com.example.pharmaplat.itemCatalog.CategoryMedicines

import com.example.pharmaplat.recycleViewAdapters.marketAdapters.*
import com.example.pharmaplat.search.Search
import kotlinx.android.synthetic.main.fragment_market.view.*


class Market : Fragment() {

    companion object {
        fun newInstance(): Market {
            return Market()
        }
    }

//    private lateinit var recyclerView: RecyclerView
//    private lateinit var adapter: MarketAdapter
//    private lateinit var layout: LinearLayoutManager


//    private lateinit var categoryRecyclerView: RecyclerView
//    private lateinit var categoryAdapter: CategoryListAdapter
//    private lateinit var categoryLayoutManager: GridLayoutManager

    private lateinit var featuredRecyclerView: RecyclerView
    private lateinit var featuredAdapter: FeaturedAdapter
    private lateinit var featuredLayoutManager: LinearLayoutManager

    private lateinit var mostViewedReyclerView: RecyclerView
    private lateinit var mostViewedAdapter: MostViewedAdapter
    private lateinit var mostViewedLayoutManager: LinearLayoutManager


    private lateinit var hotDealsRecyclerView: RecyclerView
    private lateinit var hotDealsAdapter: HotDealsAdapter
    private lateinit var hotDealsLayoutManager: LinearLayoutManager


    private var marketList: MutableList<MarketItem> = mutableListOf(
        MarketItem(
            "Meropenem", "08/22", "457 Birr",
            "Sanofi Wholesalers", "Description about the product",
            R.drawable.fiction_one
        ),
        MarketItem(
            "Amphoterci B", "03/23", "1200 Birr",
            "Sanofi Wholesalers", "Description about the product",
            R.drawable.fiction_two
        ),
        MarketItem(
            "Doxycyclin", "05/22", "415 Birr",
            "Sanofi Wholesalers", "Description about the product",
            R.drawable.fiction_three
        ),
        MarketItem(
            "Labetalol", "01/23", "895 Birr",
            "Sanofi Wholesalers", "Description about the product",
            R.drawable.fiction_four
        ),
        MarketItem(
            "Carbamazepine", "12/22", "526 Birr",
            "Sanofi Wholesalers", "Description about the product",
            R.drawable.fiction_five
        ),
        MarketItem(
            "Vancomycin", "12/21", "654 Birr",
            "Sanofi Wholesalers", "Description about the product",
            R.drawable.fiction_six
        ),
        MarketItem(
            "Metformin", "06/22", "412 Birr",
            "Sanofi Wholesalers", "Description about the product",
            R.drawable.fiction_seven
        ),
        MarketItem(
            "Tramadol", "11/24", "781 Birr",
            "Sanofi Wholesalers", "Description about the product",
            R.drawable.fiction_eight
        ),
        MarketItem(
            "Morphine", "05/22", "564 Birr",
            "Sanofi Wholesalers", "Description about the product",
            R.drawable.fiction_nine
        ),
        MarketItem(
            "Ritixzumab", "05/22", "376 Birr",
            "Sanofi Wholesalers", "Description about the product",
            R.drawable.fiction_ten
        ),
    )

    private var categoryList: MutableList<CatagoryList> = mutableListOf(
        CatagoryList("Ent", R.drawable.fiction_eight),
        CatagoryList("Nephrology", R.drawable.fiction_nine),
        CatagoryList("Radiology", R.drawable.fiction_five),
        CatagoryList("Gerontology", R.drawable.fiction_four),
        CatagoryList("Cardiology", R.drawable.fiction_three),
        CatagoryList("Neurology", R.drawable.fiction_two),
        CatagoryList("Psychiatry", R.drawable.fiction_one),
        CatagoryList("Ent", R.drawable.fiction_eight),
        CatagoryList("Nephrology", R.drawable.fiction_nine),
        CatagoryList("Radiology", R.drawable.fiction_five),
        CatagoryList("Gerontology", R.drawable.fiction_four),
        CatagoryList("Cardiology", R.drawable.fiction_three),
        CatagoryList("Neurology", R.drawable.fiction_two),
        CatagoryList("Psychiatry", R.drawable.fiction_one)

    )

    private var featuredList: MutableList<Featured> = mutableListOf(
        Featured(
            "Alema Pharmaceuticals",
            4.0F,
            "Sphygmamanometer Mercurial BP   0929903187/0910339896",
            R.drawable.sphygmamanometer
        ),
        Featured(
            "Ekram Wholesaler",
            3.7F,
            "METE NUTRITION IMPORT \n" +
                    "Newly arrival \n" +
                    "Wholesale price list \n" +
                    "\uD83D\uDD39️Whey protein weight gain..2200\n" +
                    "\uD83D\uDD39️Whey protein weight loss...2200\n" +
                    "\uD83D\uDD39️Vitamin D3........1000\n" +
                    "\uD83D\uDD39️Enhance men....2000\n" +
                    "\uD83D\uDCDE0921028367 Betty",
            R.drawable.whey_protein
        ),
        Featured(
            "Hikmet",
            4.7F,
            "Gel Sanitizers\n" +
                    "485ml \n" +
                    "280ml \n" +
                    "100ml \n" +
                    "Disinfecting wipes (50/bag with 75+/-5% alcohol) \n" +
                    "At an affordable price",
            R.drawable.sanitizer
        )
    )

    private var mostViewedList: MutableList<MostViewed> = mutableListOf(
        MostViewed(
            "Salhadin Medical Equipments",
            4.0F,
            "New entry Anesthesia Machine Available, for your inquiry please call 0938 838383",
            R.drawable.anesthesia
        ),
        MostViewed("Dilla Wholesaler",
            3.7F,
            "Vitamin D3",
            R.drawable.vitamin_d3),
        MostViewed(
            "Abyssinia Medical",
            4.3F,
            "#Glucometer\n" +
                    "#Brand- Contour care\n" +
                    "#50 test strips & 25 lancets",

            R.drawable.fiction_three
        )

    )

    private var hotDealsList: MutableList<HotDeals> = mutableListOf(
        HotDeals("Africa Pharmacy", "6 month near ex_date", R.drawable.fiction_four),
        HotDeals("Gelila Pharmacy", "I have 21 vials of Remedisvir", R.drawable.fiction_six),
        HotDeals("Sihin Pharmacy", "On sale", R.drawable.fiction_five)

    )


    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_market, container, false)


//        recyclerView = view.findViewById(R.id.recycler_view)
//        adapter = MarketAdapter(this.marketList)
//        layout = LinearLayoutManager(context!!)
//        recyclerView.layoutManager = layout
//        recyclerView.adapter = adapter

        // Category Recycler
//        categoryRecyclerView = view.findViewById(R.id.category_list_recycler_view)
//        categoryAdapter = CategoryListAdapter(categoryList)
//        categoryLayoutManager = GridLayoutManager(context, 3)
//        categoryRecyclerView.layoutManager = categoryLayoutManager
//        categoryRecyclerView.adapter = categoryAdapter


        // Featured Recycler
        featuredRecyclerView = view.findViewById(R.id.feature_recycler)
        featuredRecyclerView.setHasFixedSize(true)
        featuredAdapter = FeaturedAdapter(featuredList)
        featuredLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        featuredRecyclerView.layoutManager = featuredLayoutManager
        featuredRecyclerView.adapter = featuredAdapter


        // Most Viewed Recycler
        mostViewedReyclerView = view.findViewById(R.id.most_viewed_recycler_view)
        mostViewedReyclerView.setHasFixedSize(true)
        mostViewedAdapter = MostViewedAdapter(mostViewedList)
        mostViewedLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mostViewedReyclerView.layoutManager = mostViewedLayoutManager
        mostViewedReyclerView.adapter = mostViewedAdapter


        //Hot Deals Recycler
        hotDealsRecyclerView = view.findViewById(R.id.hot_deals_recycler_view)
        hotDealsRecyclerView.setHasFixedSize(true)
        hotDealsAdapter = HotDealsAdapter(hotDealsList)
        hotDealsLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        hotDealsRecyclerView.layoutManager = hotDealsLayoutManager
        hotDealsRecyclerView.adapter = hotDealsAdapter


//
//        catagoriesButton = view.findViewById(R.id.catagories_button)
//
//        catagoriesButton.setOnClickListener {
//            val dialog = BottomSheetDialog(MainActivity())
//            val inflater = layoutInflater.inflate(R.layout.market_catagories, null)
//            val closeButton = inflater.findViewById<Button>(R.id.cancel_sheet)
//            closeButton.setOnClickListener {
//                dialog.dismiss()
//            }
//
//            dialog.setContentView(inflater)
//            dialog.show()
//        }

        view.medicines_linear_layout.setOnClickListener {
            val intent = Intent(this.context, CategoryMedicines::class.java)
            val categoryTitle = "Medicines"
            intent.putExtra("categoryTitle", "Medicines")
            startActivity(intent)
            
        }

        view.vacancies_linear_layout.setOnClickListener {
         /*   val intent = Intent(this.context, CategoryNameList::class.java)
            intent.putExtra("categoryTitle", "Vacancies")
            startActivity(intent)*/
        }

        view.instruments_linear_layout.setOnClickListener {
            /*val intent = Intent(this.context, CategoryNameList::class.java)
            intent.putExtra("categoryTitle", "Instruments")
            startActivi*/
        }

        view.services_linear_layout.setOnClickListener {
        /*    val intent = Intent(this.context, CategoryNameList::class.java)
            intent.putExtra("categoryTitle", "Services")
            startActivity(intent)*/
        }

        view.rlt_layout.setOnClickListener {
            val intetnt = Intent(this.context, Search::class.java)
            startActivity(intetnt)
        }




        return view
    }


}






