package com.davidups.characters.domain.usecases

import com.davidups.characters.data.models.view.CharactersView
import com.davidups.characters.domain.repository.CharactersRepository
import com.davidups.core.extensions.orEmpty
import com.davidups.core.functional.State
import com.davidups.core.interactor.BaseUseCase

class GetCharacters(private val repository: CharactersRepository) :
    BaseUseCase<State<CharactersView>, GetCharacters.Params>() {

    override fun run(params: Params?) = repository.getCharacters(params?.fromPagination.orEmpty())

    data class Params(val fromPagination: Boolean)
}
