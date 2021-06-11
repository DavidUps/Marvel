package com.davidups.characters.core.di

import com.davidups.characters.data.service.CharacterService
import org.koin.dsl.module

val characterServiceModule = module {
    factory { CharacterService(get()) }
}
