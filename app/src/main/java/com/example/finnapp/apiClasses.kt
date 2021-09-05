package com.example.finnapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList

// NEWS
@Parcelize
class NewsContainer(): Parcelable{
    val news_lst = ArrayList<News>()

    fun add(to_add: News){
        news_lst.add(to_add)
    }

    fun size(): Int{
        return news_lst.size
    }

}
data class News(val category: String,
           val date: String,
           val headline: String,
           val img_url: String,
           val source: String,
           val summary: String,
           val url: String){
}

// SYMBOLS
class SymbolsContainer(){
    val symbols_lst = ArrayList<Symbol>()

    fun add(to_add: Symbol){
        symbols_lst.add(to_add)
    }

    fun size(): Int{
        return symbols_lst.size
    }
}
data class Symbol(
            val desc: String,
            val symbol: String,
            val type: String,
            val price: Double,
            val is_fav: Boolean,
            val currency: String,
//            val country: String
){
}

// COMPANY PROFILE
class CompanyContainer(){
    val company_lst = ArrayList<CompanyInfo>()

    fun add(to_add: CompanyInfo){
        company_lst.add(to_add)
    }
}
data class CompanyInfo(val name: String,
                          val country: String,
                          val currency: String,
                          val url: String,
                          val img_url: String,
                          val industry: String){

}
// CALCULATOR TO:DO
class CurrencyPairsContainer(){
    val currency_lst = ArrayList<CurrencyPair>()

    fun add(to_add: CurrencyPair){
        currency_lst.add(to_add)
    }
}
data class CurrencyPair(
        val fist: String,
        val second: String,
        val course: Double
)
