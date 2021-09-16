package com.example.finnapp

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [indexesPL.newInstance] factory method to
 * create an instance of this fragment.
 */
class indexesPL : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(
                R.layout.fragment_indexes_p_l,
                container,
                false)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val api = ApiData()
        val stock_cont = api.getSymbols(15,"pl")

        val pl_data = ArrayList<SymbolsItem>()

        for (i in stock_cont){
//            val price = api.getPrice(i.symbol.toString())

//            if(price == null){
//                val temp = SymbolsItem(i.symbol!!, i.description!!, i.currency!!)
//                plData.add(temp)
//            }
//            else {
                val temp = SymbolsItem(i.symbol!!, i.description!!, i.currency!!)//, price.c!!.round(2))
                pl_data.add(temp)
//            }
        }

        //custom adapter to list
        val adapter = SymbolsAdapter(
                requireContext(),
                R.layout.symbol_item,
                pl_data)


        val lvData: ListView = view.findViewById(R.id.indexespl_lv)
        lvData.setAdapter(adapter)

        lvData.setOnItemClickListener { parent, view, position, id ->
            Log.d("clicked PLindex: ", adapter.getItem(position)?.symbol.toString())
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
         * @return A new instance of fragment indexesPL.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            indexesPL().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}