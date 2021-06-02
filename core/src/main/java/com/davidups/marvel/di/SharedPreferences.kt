package com.davidups.marvel.di

import android.content.Context
import android.content.SharedPreferences
import com.davidups.marvel.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sharedModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            BuildConfig.APPLICATION_ID + "-" + BuildConfig.ENVIRONMENT,
            Context.MODE_PRIVATE
        )
    }
}
