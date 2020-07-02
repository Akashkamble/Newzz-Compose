package com.akash.newzz_compose

import com.akash.newzz.data.BaseApplication
import com.akash.newzz_compose.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Akash on 06/06/20
 */
class NewzzApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@NewzzApplication)
            modules(appModule)
        }
    }
}
