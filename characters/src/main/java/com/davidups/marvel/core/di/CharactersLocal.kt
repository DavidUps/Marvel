package com.davidups.marvel.core.di

import androidx.room.Room
import com.davidups.marvel.core.database.CharactersDatabase
import com.davidups.marvel.data.local.CharacterLocal
import org.koin.dsl.module

val characterLocalModule = module {

    factory { CharacterLocal(get()) }

    factory {
        Room.databaseBuilder(
            get(),
            CharactersDatabase::class.java,
            "characters"
        )
            .build()
    }
}
