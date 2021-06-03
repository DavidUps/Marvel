package com.davidups.marvel.core.di

import com.davidups.marvel.data.service.CharacterService
import com.davidups.marvel.domain.repository.CharactersRepository
import com.davidups.marvel.domain.repository.CharactersRepositoryImp
import org.koin.dsl.module

val characterServiceModule = module {
    factory { CharacterService(get()) }
}
