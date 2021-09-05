package com.example.finnapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.*
import android.webkit.WebViewClient
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM = "news"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param: NewsContainer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val test = "string"
        if (savedInstanceState != null) {
            param = savedInstanceState.getParcelable(ARG_PARAM)
        }
//        val api = ApiData()


        Log.d("got data", param.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(
            R.layout.fragment_home,
            container,
            false)
        val data = arguments?.get(ARG_PARAM)
//
        Log.d("data", data.toString())
//        val api = ApiData()
////        // news
//        val news_cont = api.getNews()
//
        val lvData: ListView = view.findViewById(R.id.home_lv)
////        Log.d("got data 1", param.toString())
//        // list of data
        val homeData = ArrayList<String>()
        homeData.add("Pfizer Covid booster shots will likely be ready Sept. 20, but Moderna may be delayed, Fauci says:https://www.cnbc.com/2021/09/05/pfizer-covid-booster-shots-likely-ready-sept-20-anthony-fauci-says.html")
//        for(item in news_cont.news_lst){
//            homeData.add(item.headline + ':' + item.url)
//        }
//        val newsData = ArrayList<NewsItem>()
//
//        for (item in news_cont.news_lst){
//
//        }


        val adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            homeData)

        lvData.setAdapter(adapter)


        lvData.setOnItemClickListener { parent, view, position, id ->
            val element = adapter.getItem(position)
            val url = element!!.split(":")[1]
            browser.settings.javaScriptEnabled = true
            browser.webViewClient = WebViewClient()
            browser.loadUrl(url)
        }

//        view.text sset on lcik
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(news_lst : NewsContainer) {
            val args = Bundle()
            args.putParcelable(ARG_PARAM, news_lst)
            val fragment = HomeFragment()
            fragment.arguments = args
            fragment.requireArguments()
            }
    }
    }




