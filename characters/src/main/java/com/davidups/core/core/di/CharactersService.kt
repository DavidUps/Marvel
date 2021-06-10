package com.davidups.marvel.core.di

import com.davidups.marvel.data.service.CharacterService
import org.koin.dsl.module

val characterServiceModule = module {
    factory { CharacterService(get()) }
}
