package com.example.finnapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CompanyAdapter(var ctx: Context, var resources: Int, var items: ArrayList<CompanyItem>):
        ArrayAdapter<CompanyItem>(ctx, resources, items){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater = LayoutInflater.from(ctx)
        val view = layoutInflater.inflate(resources, null)

        val symbol = view.findViewById<TextView>(R.id.stock_symbol)
        val descritption = view.findViewById<TextView>(R.id.stock_description)
        val price = view.findViewById<TextView>(R.id.stock_price)
        val currency = view.findViewById<TextView>(R.id.stock_currency)

        var mItem: CompanyItem = items[position]

        symbol.text = mItem.symbol
        descritption.text = mItem.desc
        price.text = mItem.price.toString()
        currency.text = mItem.currency

        return view
    }
}

class CompanyItem(val symbol:String, val desc: String, val price: Int, val currency:String) {
//    val header
//    val summary
//    val url = url
}