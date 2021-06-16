package com.davidups.characters.domain.repository

import com.davidups.characters.data.models.view.CharactersView
import com.davidups.core.functional.State
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

<<<<<<< Updated upstream
    fun getCharacters(offset: Int?, romPagination: Boolean): Flow<State<CharactersView>>
=======
    fun getCharacters(fromPagination: Boolean): Flow<State<CharactersView>>
>>>>>>> Stashed changes
}
