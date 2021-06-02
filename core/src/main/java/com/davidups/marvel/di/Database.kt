package com.davidups.marvel.di

import androidx.room.Room
import com.davidups.marvel.BuildConfig
import com.davidups.marvel.database.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            BuildConfig.DATA_BASE
        )
            .build()
    }

//    factory { PeopleLocal(get()) }
}
