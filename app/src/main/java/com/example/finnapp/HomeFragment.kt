package com.example.finnapp

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.*
import android.webkit.WebViewClient
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
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

        Log.d("data", data.toString())

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val api = ApiData()
        val news_cont = api.getNews(10).news_lst

        val lvData: ListView = view.findViewById(R.id.home_lv)
        val homeData = ArrayList<NewsItem>()

        for (i in news_cont) {
            val temp = NewsItem(i.headline, i.summary, i.img_url, i.date.split(" ")[0], i.url)
            homeData.add(temp)
        }

        val adapter = NewsAdapter(
                requireContext(),
                R.layout.news_item,
                homeData)

        lvData.setAdapter(adapter)


        lvData.setOnItemClickListener { parent, view, position, id ->
//            val element = adapter.getItem(position)
//            val url = element!!.split(":")[1]
//            browser.settings.javaScriptEnabled = true
//            browser.webViewClient = WebViewClient()
//            browser.loadUrl(url)
            Log.d("clicked", adapter.getItem(position)?.article_url.toString())
        }

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
        fun newInstance(news_lst: NewsContainer) {
            val args = Bundle()
            args.putParcelable(ARG_PARAM, news_lst)
            val fragment = HomeFragment()
            fragment.arguments = args
            fragment.requireArguments()
            }
    }
    }




