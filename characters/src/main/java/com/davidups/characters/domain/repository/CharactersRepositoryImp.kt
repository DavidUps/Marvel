package com.davidups.characters.domain.repository

import com.davidups.characters.domain.datasource.CharactersDataSource

class CharactersRepositoryImp(
    private val charactersDataSource: CharactersDataSource
) : CharactersRepository {

    override fun getCharacters(offset: Int?, fromPagination: Boolean) =
        charactersDataSource.getCharacters(offset, fromPagination)
}
