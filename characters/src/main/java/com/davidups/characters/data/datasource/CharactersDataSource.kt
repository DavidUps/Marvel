package com.davidups.characters.data.datasource

import com.davidups.characters.data.models.view.CharactersView
import com.davidups.core.functional.State
import kotlinx.coroutines.flow.Flow

interface CharactersDataSource {

    fun getCharacters(fromPagination: Boolean): Flow<State<CharactersView>>
}
