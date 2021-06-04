package com.davidups.marvel.features.character.views.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.davidups.marvel.data.models.view.CharactersView
import com.davidups.marvel.domain.usecases.GetCharacters
import com.davidups.marvel.exception.Failure
import com.davidups.marvel.extensions.cancelIfActive
import com.davidups.marvel.functional.Error
import com.davidups.marvel.functional.Success
import com.davidups.marvel.interactor.UseCase
import com.davidups.marvel.platform.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharacters: GetCharacters
) : BaseViewModel() {

    private var getCharactersJob: Job? = null

    private var _characters = MutableLiveData<CharactersView>()
    val characters get() = _characters

    fun getCharacters() {
        getCharactersJob.cancelIfActive()
        getCharactersJob = viewModelScope.launch {
            getCharacters(UseCase.None())
                .onStart { handleShowSpinner(true) }
                .onCompletion { handleShowSpinner(false) }
                .catch { handleFailure(Failure.Throwable(it)) }
                .collect { result ->
                    when (result) {
                        is Success<CharactersView> -> _characters.value = result.data
                        is Error -> handleFailure(result.failure)
                    }
                }
        }
    }
}