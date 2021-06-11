package com.davidups.characters.core.di

import com.davidups.characters.domain.usecases.GetCharacters
import org.koin.dsl.module

val characterUseCasesModule = module {
    factory { GetCharacters(get()) }
}
