package com.davidups.marvel.domain.datasource

import com.davidups.marvel.data.models.view.CharactersView
import com.davidups.marvel.functional.State
import kotlinx.coroutines.flow.Flow

interface CharactersDataSource {

    fun getCharacters(offset: Int?): Flow<State<CharactersView>>

}
