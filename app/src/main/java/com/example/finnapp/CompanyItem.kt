package com.example.finnapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class CompanyAdapter(var ctx: Context, var resources: Int, var items: ArrayList<CompanyItem>):
        ArrayAdapter<CompanyItem>(ctx, resources, items){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater = LayoutInflater.from(ctx)
        val view = layoutInflater.inflate(resources, null)

        val img = view.findViewById<ImageView>(R.id.compImg)
        val symbol = view.findViewById<TextView>(R.id.comp_symbol)
        val name = view.findViewById<TextView>(R.id.comp_name)
        val ind = view.findViewById<TextView>(R.id.comp_industry)
        val country = view.findViewById<TextView>(R.id.comp_country)

        var mItem: CompanyItem = items[position]

        Picasso.get().load(mItem.logo).into(img)
        symbol.text = mItem.symbol
        name.text = mItem.name
        ind.text = mItem.industry
        country.text = "Country: " + mItem.country

        return view
    }
}

class CompanyItem(val name: String, val symbol: String, val logo: String, val industry: String, val country: String) {
}