package com.davidups.core.core.di

import androidx.room.Room
import com.davidups.core.core.database.CharactersDatabase
import com.davidups.core.data.local.CharacterLocal
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
