package com.davidups.marvel.domain.repository

import com.davidups.marvel.domain.datasource.CharactersDataSource

class CharactersRepositoryImp(
    private val charactersDataSource: CharactersDataSource
) : CharactersRepository {

    override fun getCharacters(offset: Int?, fromPagination: Boolean) =
        charactersDataSource.getCharacters(offset, fromPagination)
}