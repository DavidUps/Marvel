package com.davidups.marvel

import android.app.Application
import com.davidups.marvel.core.di.characterDataSourceModule
import com.davidups.marvel.core.di.characterLocalModule
import com.davidups.marvel.core.di.characterRepositoryModule
import com.davidups.marvel.core.di.characterServiceModule
import com.davidups.marvel.core.di.characterUseCasesModule
import com.davidups.marvel.core.di.viewModelModule
import com.davidups.marvel.di.networkModule
import com.davidups.marvel.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        /*val moduleList = mutableListOf(viewModelModule)
        moduleList.addAll(charactersModules)*/
        startKoin {
            androidLogger()
            androidContext(this@AndroidApplication)
            modules(
                listOf(
                    viewModelModule,
                    characterUseCasesModule,
                    characterDataSourceModule,
                    characterLocalModule,
                    characterRepositoryModule,
                    characterServiceModule,
                    networkModule,
                    sharedModule
                )
            )
        }
    }
}
