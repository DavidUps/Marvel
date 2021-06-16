package com.davidups.characters.core.di

import androidx.room.Room
import com.davidups.characters.core.database.CharactersDatabase
import com.davidups.characters.data.local.CharacterLocal
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
