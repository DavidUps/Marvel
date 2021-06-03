package com.davidups.marvel.domain.usecases

import com.davidups.marvel.data.models.view.CharactersView
import com.davidups.marvel.domain.repository.CharactersRepository
import com.davidups.marvel.functional.State
import com.davidups.marvel.interactor.UseCase

class GetCharacters(private val repository: CharactersRepository) :
    UseCase<State<CharactersView>, UseCase.None>() {

    override fun run(params: None?) = repository.getCharacters()

}
