package com.davidups.core.di

import android.content.Context
import android.content.SharedPreferences
import com.davidups.core.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sharedModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            BuildConfig.DATA_BASE, Context.MODE_PRIVATE
        )
    }
}
