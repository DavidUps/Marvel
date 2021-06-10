package com.davidups.characters.domain.repository

import com.davidups.characters.data.models.view.CharactersView
import com.davidups.core.functional.State
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getCharacters(offset: Int?, romPagination: Boolean): Flow<State<CharactersView>>
}
