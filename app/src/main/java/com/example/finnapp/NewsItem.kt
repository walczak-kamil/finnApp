package com.example.finnapp

import android.content.Context
import android.icu.number.NumberFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class NewsAdapter(var ctx: Context, var resources: Int, var items: ArrayList<NewsItem>):
        ArrayAdapter<NewsItem>(ctx, resources, items){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater = LayoutInflater.from(ctx)
        val view = layoutInflater.inflate(resources, null)


        val img = view.findViewById<ImageView>(R.id.newsImg)
        val header = view.findViewById<TextView>(R.id.header)
        val summary = view.findViewById<TextView>(R.id.summary)

        var mItem: NewsItem = items[position]

        Picasso.get().load(mItem.url).into(img)
        header.text = mItem.header
        summary.text = mItem.summary

        return view
    }
        }

class NewsItem(val header:String, val summary: String,  val url:String, val article:String) {
//    val header
//    val summary
//    val url = url
}