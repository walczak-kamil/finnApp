package com.example.finnapp

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Pair
import androidx.annotation.RequiresApi
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import io.finnhub.api.apis.DefaultApi
import io.finnhub.api.infrastructure.ApiClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.sql.DriverManager.println
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var listener: NavController.OnDestinationChangedListener
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.homeFragment)
        navigationView.setupWithNavController(navController)
        drawerLayout = findViewById(R.id.drawer_layout)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)




        //START JOB
        val job = CoroutineScope(Dispatchers.IO).launch {

            val api = ApiData()
            // news
            val news_cont = api.getNews()

            //symbols and postprocessing
            val pl_symbols = api.getSymbols("pl")
            pl_symbols.toString()
//            val paris = api.getPairs("PLN")
//            Log.d("pairs", paris.toString())
            Log.d("news_size", news_cont.size().toString())
//            Log.d("symbols_size", symbols_cont.size().toString())
            //val xd = api.getPairs("USD")

            val bundle = Bundle()
            bundle.putParcelable("news", news_cont)
            val frag = HomeFragment.newInstance(news_cont)

            Log.d("bundle test", news_cont.toString())
        }

        job.start()

        if(job.isCompleted){
            job.cancel()
        }



        listener = NavController.OnDestinationChangedListener{controller, destination, arguments ->
            if(destination.id == R.id.homeFragment){
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.design_default_color_primary_dark)))
            }else if (destination.id == R.id.indexes){
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.design_default_color_error)))
//                setContentView(R.layout.fragment_indexes)
            }
        }
    }


    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.homeFragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}



