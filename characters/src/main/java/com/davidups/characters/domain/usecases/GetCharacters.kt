package com.davidups.characters.domain.usecases

import com.davidups.characters.data.models.view.CharactersView
import com.davidups.characters.domain.repository.CharactersRepository
import com.davidups.core.extensions.orEmpty
import com.davidups.core.functional.State
import com.davidups.core.interactor.UseCase

class GetCharacters(private val repository: CharactersRepository) :
    UseCase<State<CharactersView>, GetCharacters.Params>() {

    override fun run(params: Params?) = repository.getCharacters(params?.offset, params?.fromPagination.orEmpty())

    data class Params(val offset: Int?, val fromPagination: Boolean)
}
