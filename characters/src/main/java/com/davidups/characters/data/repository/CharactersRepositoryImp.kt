package com.davidups.characters.data.repository

import com.davidups.characters.data.datasource.CharactersDataSource
import com.davidups.characters.domain.repository.CharactersRepository

class CharactersRepositoryImp(
    private val charactersDataSource: CharactersDataSource
) : CharactersRepository {

    override fun getCharacters(fromPagination: Boolean) =
        charactersDataSource.getCharacters(fromPagination)
}
