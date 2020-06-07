package com.akash.newzz_compose

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.akash.newzz_compose.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Akash on 06/06/20
 */
class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instances = this
        startKoin{
            androidLogger()
            androidContext(this@NewsApplication)
            modules(appModule)
        }
    }


    companion object {
        lateinit var instances: NewsApplication


        fun isNetworkConnected(): Boolean {
            val connectivityManager =
                instances.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
}