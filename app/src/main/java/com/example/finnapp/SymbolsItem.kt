package com.example.finnapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class SymbolsAdapter(var ctx: Context, var resources: Int, var items: ArrayList<SymbolsItem>):
        ArrayAdapter<SymbolsItem>(ctx, resources, items){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater = LayoutInflater.from(ctx)
        val view = layoutInflater.inflate(resources, null)

        val symbol = view.findViewById<TextView>(R.id.stock_symbol)
        val descritption = view.findViewById<TextView>(R.id.stock_description)
        val price = view.findViewById<TextView>(R.id.stock_price)
        val currency = view.findViewById<TextView>(R.id.stock_currency)

        var mItem: SymbolsItem = items[position]

        symbol.text = mItem.symbol
        descritption.text = mItem.desc
        price.text = mItem.price.toString()
        currency.text = mItem.currency

        return view
    }
}

class SymbolsItem(val symbol:String, val desc: String, val currency:String, val price: Float = 0.0f) {
}