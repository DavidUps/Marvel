package com.davidups.core

import android.app.Application
import com.davidups.characters.core.di.characterDataSourceModule
import com.davidups.characters.core.di.characterLocalModule
import com.davidups.characters.core.di.characterRepositoryModule
import com.davidups.characters.core.di.characterServiceModule
import com.davidups.characters.core.di.characterUseCasesModule
import com.davidups.core.di.networkModule
import com.davidups.core.di.sharedModule
import com.davidups.core.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AndroidApplication)
            modules(
                getModules()
            )
        }
    }

    private fun getModules(): List<Module> {
        val appModules = mutableListOf(viewModelModule)
        val charactersModules = mutableListOf(
            characterUseCasesModule,
            characterDataSourceModule,
            characterLocalModule,
            characterRepositoryModule,
            characterServiceModule
        )

        val coreModules = mutableListOf(
            networkModule,
            sharedModule,
        )
        val modules = mutableListOf<Module>()
        modules.addAll(appModules)
        modules.addAll(charactersModules)
        modules.addAll(coreModules)
        return modules
    }
}
