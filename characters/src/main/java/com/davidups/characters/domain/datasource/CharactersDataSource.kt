package com.davidups.characters.domain.datasource

import com.davidups.characters.data.models.view.CharactersView
import com.davidups.core.functional.State
import kotlinx.coroutines.flow.Flow

interface CharactersDataSource {

    fun getCharacters(offset: Int?, fromPagination: Boolean): Flow<State<CharactersView>>
}
