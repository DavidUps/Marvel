package com.davidups.marvel.core.di

import com.davidups.marvel.features.character.views.viewmodel.CharactersViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CharactersViewModel(get()) }
}
