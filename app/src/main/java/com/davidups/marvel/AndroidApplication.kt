package com.davidups.marvel

import android.app.Application
import com.davidups.marvel.core.di.*
import com.davidups.marvel.di.networkModule
import com.davidups.marvel.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AndroidApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AndroidApplication)
            modules(listOf(
                networkModule,
                sharedModule
            ))
        }
    }
}
