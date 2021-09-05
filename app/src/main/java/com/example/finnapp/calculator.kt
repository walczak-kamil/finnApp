package com.example.finnapp

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_calculator.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [calculator.newInstance] factory method to
 * create an instance of this fragment.
 */
class calculator : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var exchange: Double = 1.0
    private var result: Double = 1.0
    private var inputMoney: Double = 0.0
    private var currency1: String = ""
    private var currency2: String = ""

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

        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiCalc = ApiData()

        exchange = 1.5

//        if(inputAmount.editText.toString() != null){
//            inputMoney = inputAmount.toString().toDouble()
//        }
        currency1 = spinner1.selectedItem.toString()
        Log.d("selected", currency1.toString())
//        val onItemSelectedListener = spinner1.onItemSelectedListener
//        val onItemSelectedListener2 = spinner2.onItemSelectedListener

//        currency1 = onItemSelectedListener
//        currency2 = onItemSelectedListener2.toString()
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val button = buttonConvert
        button.setOnClickListener {

            Log.d("selected", spinner1.selectedItem.toString())
            Log.d("selected2", spinner2.selectedItem.toString())

            currency1 = spinner1.selectedItem.toString()
            currency2 = spinner2.selectedItem.toString()


            val currency_pairs = apiCalc.getPairs(currency1) as? Map<*, *>
            exchange = currency_pairs!![currency2].toString().toDouble()
            if (inputAmount.editableText.isNotBlank()) {
                val amount = inputAmount.text.toString().toDouble()
                result = (exchange * amount)
                result = String.format("%.2f", result).toDouble()
                textViewResult.setText(
                        "$amount $currency1  =  $result $currency2"
                )
            }
            else{
                val ctx = context
                Toast.makeText(ctx, "Empty amount - insert number", Toast.LENGTH_LONG)

            }
        }

    }

    override fun onResume() {
        super.onResume()
//        val currency1 = spinner1.selectedItem.toString()
//        val currency2 = spinner2.selectedItem.toString()
//        val amount = enterAmount.text.toString().toDouble()
//        Log.d("calc info", currency1 + currency2 + amount.toString())

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment calculator.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            calculator().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}