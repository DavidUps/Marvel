package com.davidups.characters.domain.repository

import com.davidups.characters.data.datasource.CharactersDataSource


class CharactersRepositoryImp(
    private val charactersDataSource: CharactersDataSource
) : CharactersRepository {

    override fun getCharacters(fromPagination: Boolean) =
        charactersDataSource.getCharacters(fromPagination)
}
