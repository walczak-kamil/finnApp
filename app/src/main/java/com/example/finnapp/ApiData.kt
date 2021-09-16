package com.example.finnapp

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import io.finnhub.api.apis.DefaultApi
import io.finnhub.api.infrastructure.ApiClient
import io.finnhub.api.models.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.round

class ApiData {

    init {
        // API client access token
        ApiClient.apiKey["token"] = "c4ha2v2ad3ianssq030g"
    }
    private val apiClient = DefaultApi()

    //GET functions (NEWS, SYMBOLS, PRICE, COMPANIES, CHART DATA)
    fun getNews(counter: Int, newsCategory: String = "general"): NewsContainer{
        val news_lst = NewsContainer()
        val response_news = apiClient.marketNews(newsCategory,0)
        var cnt = counter

        for (news in response_news){
            if(news.category != "" &&
                    news.datetime != null &&
                    news.headline != "" &&
                    news.image != "" &&
                    news.source != "" &&
                    news.summary != "" &&
                    news.url != ""
                    ) {
                            val category = news.category!!
                            val date = SimpleDateFormat().format(news.datetime!! * 1000)
                            val headline = news.headline!!
                            val img_url = news.image!!
                            val source = news.source!!
                            val summary = news.summary!!
                            val url = news.url!!

                            val to_add = News(category, date, headline, img_url, source, summary, url)
                            news_lst.add(to_add)
                            cnt--
                            if (cnt == 0){
                                break
                            }
            }
            else
            {
                continue
            }
        }

        return news_lst
    }

    fun getSymbols(counter: Int, market: String = "all"): ArrayList<StockSymbol>{
        val random_symbols = ArrayList<StockSymbol>()
        var cnt = counter

        if (market == "all"){
            val exchange_lst = listOf("US", "SS", "HK", "BR", "TO", "L", "AX", "WA")
            for (exchange in exchange_lst) {
                val response_symbols = apiClient.stockSymbols(exchange, null, null, null)
                for (symbol in response_symbols){
                    if(symbol.currency != "" &&
                            symbol.description != "" &&
                            symbol.symbol != "" &&
                            symbol.type != ""){
                                random_symbols.add(symbol)
                        cnt--
                        if(cnt == 0){
                            break
                        }
                    }
                    else{
                        continue
                    }
                }
                if(cnt == 0){
                    break
                }
            }
        }
        else{
                val response_symbols = apiClient.stockSymbols("WA", null, null, null)
                for (symbol in response_symbols){
                    random_symbols.add(symbol)
                    cnt--
                    if (cnt == 0){
                        break
                    }
                }
        }

        return random_symbols
    }

    fun getPairs(symbol: String): Any? {
        val response_pairs = apiClient.forexRates(symbol)
        val pairs = response_pairs.quote
        Log.d("currency", pairs.toString())

        return pairs
    }

    fun getPrice(symbol: String): Quote{
        return apiClient.quote(symbol)
    }

    fun getCompanies(counter:Int): ArrayList<CompanyProfile2> {
        val symbols = this.getSymbols(70)
        var cnt = counter
        val list = ArrayList<CompanyProfile2>()

        for (symbol in symbols){
            val response = apiClient.companyProfile2(symbol.symbol,null,null)
            if (response.country != null &&
                    response.finnhubIndustry != null &&
                    response.logo != "" &&
                    response.name != null &&
                    response.ticker != null &&
                    response.weburl != ""
                    ){
                        list.add(response)
                        cnt--
            }

            if(cnt == 0){
                break
            }
        }
        return list
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getChartData(): StockCandles {
        val endDate = LocalDate.now()
        Log.d("date", endDate.toString())
        val quotes = apiClient.stockCandles("AAPL", "D", 1577910983, 1591852249)
        return quotes
    }


}

fun Float.round(decimals: Int): Float {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return (round(this * multiplier) / multiplier).toFloat()
}