package com.davidups.characters.core.di

import com.davidups.characters.domain.repository.CharactersRepository
import com.davidups.characters.domain.repository.CharactersRepositoryImp
import org.koin.dsl.module

val characterRepositoryModule = module {
    factory<CharactersRepository> { CharactersRepositoryImp(get()) }
}
