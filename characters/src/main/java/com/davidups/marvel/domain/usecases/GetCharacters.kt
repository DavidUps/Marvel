package com.davidups.marvel.domain.usecases

import com.davidups.marvel.data.models.view.CharactersView
import com.davidups.marvel.domain.repository.CharactersRepository
import com.davidups.marvel.extensions.orEmpty
import com.davidups.marvel.functional.State
import com.davidups.marvel.interactor.UseCase

class GetCharacters(private val repository: CharactersRepository) :
    UseCase<State<CharactersView>, GetCharacters.Params>() {

    override fun run(params: Params?) = repository.getCharacters(params?.offset, params?.fromPagination.orEmpty())

    data class Params(val offset: Int?, val fromPagination: Boolean)

}
