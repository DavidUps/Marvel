package com.davidups.core

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.davidups.characters.data.models.entity.CharactersEntity
import com.davidups.characters.data.models.view.CharactersView
import com.davidups.characters.data.repository.CharactersRepositoryImp
import com.davidups.characters.domain.usecases.GetCharacters
import com.davidups.core.exception.Failure
import com.davidups.marvel.features.character.views.viewmodel.CharacterViewModel
import com.davidups.core.functional.Success
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: CharacterViewModel

    private var repository = mock<CharactersRepositoryImp>()
    private var getCharacters = mock<GetCharacters>()

    private val charactersObserver = mock<Observer<CharactersView>>()
    private val isErrorObserver = mock<Observer<Failure>>()

    @Before
    fun setup() {
        getCharacters = GetCharacters(repository)
        viewModel = CharacterViewModel(getCharacters).apply {
            characters.observeForever(charactersObserver)
            failure.observeForever(isErrorObserver)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should emit get characters`() =
        coroutinesRule.dispatcher.runBlockingTest {

            val expectedCharacters =
                Success(CharactersEntity.empty().toCharacters().toCharactersView())
            val expectedError = Failure.Throwable(Throwable())

            val channel = Channel<Success<CharactersView>>()
            val flow = channel.consumeAsFlow()

            doReturn(flow)
                .whenever(repository)
                .getCharacters(true)

            launch {
                channel.send(expectedCharacters)
                channel.close(expectedError.throwable)
            }

            viewModel.getCharacters(true)

            verify(charactersObserver).onChanged(expectedCharacters.data)
            verify(isErrorObserver).onChanged(expectedError)
        }
}
