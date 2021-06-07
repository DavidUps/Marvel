package com.davidups.marvel.features.character.views.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.davidups.marvel.data.models.view.CharactersView
import com.davidups.marvel.domain.usecases.GetCharacters
import com.davidups.marvel.exception.Failure
import com.davidups.marvel.extensions.cancelIfActive
import com.davidups.marvel.extensions.orEmpty
import com.davidups.marvel.functional.Error
import com.davidups.marvel.functional.Success
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

    fun getCharacters(fromPagination: Boolean = false) {
        getCharactersJob.cancelIfActive()
        getCharactersJob = viewModelScope.launch {
            getCharacters(GetCharacters.Params(calculateOffset(), fromPagination))
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

    private fun calculateOffset() = _characters.value?.offset?.let { it + 10 }.orEmpty()
}
