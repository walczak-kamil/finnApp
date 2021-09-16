package com.example.finnapp

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import io.finnhub.api.models.CompanyProfile2

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [companies.newInstance] factory method to
 * create an instance of this fragment.
 */
class companies : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(
                R.layout.fragment_companies,
                container,
                false)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val api = ApiData()
        val comp_cont = api.getCompanies(10)

        val companiesData = ArrayList<CompanyItem>()

        for (i in comp_cont){
            val temp = CompanyItem(i.name!!, i.ticker!!, i.logo!!, i.finnhubIndustry!!, i.country!!)
            companiesData.add(temp)
        }

        val adapter = CompanyAdapter(
                requireContext(),
                R.layout.company_item,
                companiesData)

        val lvData: ListView = view.findViewById(R.id.companies_lv)
        lvData.setAdapter(adapter)

        lvData.setOnItemClickListener { parent, view, position, id ->
            Log.d("clicked company: ", adapter.getItem(position)?.name.toString())
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
         * @return A new instance of fragment companies.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            companies().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}