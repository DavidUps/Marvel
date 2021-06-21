package com.davidups.marvel.features.character.views.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.davidups.characters.data.models.view.CharactersView
import com.davidups.characters.domain.usecases.GetCharacters
import com.davidups.core.exception.Failure
import com.davidups.core.extensions.cancelIfActive
import com.davidups.core.extensions.orEmpty
import com.davidups.core.functional.Error
import com.davidups.core.functional.Success
import com.davidups.core.platform.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharacters: GetCharacters
) : BaseViewModel() {

    private var getCharactersJob: Job? = null

    private var _characters = MutableLiveData<CharactersView>()
    val characters get() = _characters

    fun getCharacters(fromPagination: Boolean = false) {
        getCharactersJob.cancelIfActive()
        getCharactersJob = viewModelScope.launch {
            getCharacters(GetCharacters.Params(fromPagination))
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
