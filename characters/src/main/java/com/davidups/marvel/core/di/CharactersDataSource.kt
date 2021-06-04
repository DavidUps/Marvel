package com.davidups.marvel.core.di

import com.davidups.marvel.domain.datasource.CharactersDataSource
import com.davidups.marvel.domain.datasource.CharactersDataSourceImp
import org.koin.dsl.module

val characterDataSourceModule = module {
    factory<CharactersDataSource> { CharactersDataSourceImp(get(), get(), get()) }
}
