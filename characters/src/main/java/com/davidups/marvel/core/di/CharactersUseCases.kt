package com.davidups.marvel.core.di

import com.davidups.marvel.domain.usecases.GetCharacters
import org.koin.dsl.module

val characterUseCasesModule = module {
    factory { GetCharacters(get()) }
}
