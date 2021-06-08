package com.davidups.marvel

import com.davidups.marvel.data.models.entity.CharactersEntity
import com.davidups.marvel.data.models.view.CharactersView
import com.davidups.marvel.domain.repository.CharactersRepository
import com.davidups.marvel.domain.usecases.GetCharacters
import com.davidups.marvel.features.character.views.viewmodel.CharacterViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.davidups.marvel.functional.Success
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterViewModelTest {

    @get:Rule
    var coroutinesRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: CharacterViewModel

    private var repository = mock<CharactersRepository>()
    private var getMovies = mock<GetCharacters>()

    private val charactersObserver = mock<Observer<CharactersView>>()

    @Before
    fun setup() {
        getMovies = mock()
        viewModel = CharacterViewModel(getMovies).apply {
            characters.observeForever { charactersObserver }
        }
    }

    @Test
    fun `should emit error on get characters`() =
        coroutinesRule.dispatcher.runBlockingTest {

            val expectedCharacters =
                Success(CharactersEntity.empty().toCharacters().toCharactersView())

            val channel = Channel<Success<CharactersView>>()
            val flow = channel.consumeAsFlow()

            doReturn(flow)
                .whenever(repository)
                .getCharacters(null, false)

            launch {
                channel.send(expectedCharacters)
                channel.close(Throwable())
            }

            viewModel.getCharacters(false)

            verify(charactersObserver).onChanged(expectedCharacters.data)
        }
}