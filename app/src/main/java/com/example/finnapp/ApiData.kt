package com.example.finnapp

import android.annotation.SuppressLint
import android.util.Log
import java.time.format.DateTimeFormatter.ISO_INSTANT
import io.finnhub.api.apis.DefaultApi
import io.finnhub.api.infrastructure.ApiClient
import io.finnhub.api.models.MarketNews
import io.finnhub.api.models.Quote
import io.finnhub.api.models.StockSymbol
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random.Default.nextInt

class ApiData {

    init {
        // API client access token
        ApiClient.apiKey["token"] = "c4ha2v2ad3ianssq030g"
    }
    private val apiClient = DefaultApi()


    //GET functions (NEWS, SYMBOLS, PRICE, CURRENCY PAIRS)
    fun getNews(newsCategory: String = "general"): NewsContainer{
        val news_lst = NewsContainer()
        val response_news = apiClient.marketNews(newsCategory,0)

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
            }
            else
            {
                continue
            }
        }

        return news_lst
    }

    fun getSymbols(market: String = "all"): MutableList<StockSymbol>{
        val random_symbols = mutableListOf<StockSymbol>()
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
                    }
                    else{
                        continue
                    }
                }
            }
        }
        else{
                val response_symbols = apiClient.stockSymbols("WA", null, null, null)
                for (symbol in response_symbols){
                    random_symbols.add(symbol)
                }
        }

        return random_symbols
    }

    fun getPairs(symbol: String = "USD"): Boolean{
        val response_pairs = apiClient.forexRates(symbol)
        val main_currency = response_pairs.base
        val pairs = response_pairs.quote
        Log.d("currency", main_currency.toString() + pairs.toString() )

        return true
    }
    fun getPrice(symbol: String): Quote{
        return apiClient.quote(symbol)
    }


}