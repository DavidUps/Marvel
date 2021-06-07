package com.davidups.marvel.domain.repository

import com.davidups.marvel.data.models.view.CharactersView
import com.davidups.marvel.functional.State
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getCharacters(offset: Int?, romPagination: Boolean): Flow<State<CharactersView>>
}
