package com.example.finnapp

import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import java.security.KeyStore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Chart.newInstance] factory method to
 * create an instance of this fragment.
 */
class Chart : Fragment() {
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_chart, container, false)

        var chart_data = ArrayList<CandleEntry>()

        val api = ApiData()
        val candles = api.getChartData()

        if(candles.s.toString() == "ok"){
            val list_size = candles.o?.size
            for (i in 0..list_size!!-1){

                chart_data.add(CandleEntry(
                    i.toFloat(),
                    candles.h?.get(i)!!,
                    candles.l?.get(i)!!,
                    candles.o?.get(i)!!,
                    candles.c?.get(i)!!
                    )
                )
            }
        }
        Log.d("candles", candles.toString())

//        chart_data.add(CandleEntry(0f, 1.1074f,1.09897f,1.0996f, 1.10713f))
//        chart_data.add(CandleEntry(1f,1.1075f,  1.1013f, 1.107f,  1.10288f))
//        chart_data.add(CandleEntry(2f, 1.10729f,  1.10223f,  1.10269f, 1.10397f))

        // chart view settings
        var candleDataSet = CandleDataSet(chart_data, "Stock Price Candle Chart")
        candleDataSet.setColor(Color.rgb(80, 80, 80))
        candleDataSet.setShadowColor(getResources().getColor(R.color.black))
        candleDataSet.setShadowWidth(0.8f)
        candleDataSet.setDecreasingColor(getResources().getColor(R.color.red))
        candleDataSet.setDecreasingPaintStyle(Paint.Style.FILL)
        candleDataSet.setIncreasingColor(getResources().getColor(R.color.green))
        candleDataSet.setIncreasingPaintStyle(Paint.Style.FILL)
        candleDataSet.setNeutralColor(Color.LTGRAY)
        candleDataSet.setDrawValues(false)

        var candleData = CandleData(candleDataSet)

        val chart = view.findViewById<CandleStickChart>(R.id.chart_candle)

        chart.setDrawBorders(true)
        chart.data = candleData
        chart.invalidate()

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Chart.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                Chart().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}