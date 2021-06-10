package com.davidups.core.core.di

import com.davidups.characters.domain.datasource.CharactersDataSource
import com.davidups.characters.domain.datasource.CharactersDataSourceImp
import org.koin.dsl.module

val characterDataSourceModule = module {
    factory<CharactersDataSource> { CharactersDataSourceImp(get(), get(), get()) }
}
