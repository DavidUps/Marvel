package com.davidups.marvel

import com.davidups.marvel.data.models.entity.CharactersEntity
import com.davidups.marvel.data.models.view.CharactersView
import com.davidups.marvel.domain.repository.CharactersRepository
import com.davidups.marvel.domain.usecases.GetCharacters
import com.davidups.marvel.features.character.views.viewmodel.CharacterViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.davidups.marvel.functional.Success
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class CharacterViewModelTest {

    @get:Rule
    var coroutinesRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: CharacterViewModel

    private var repository = mock<CharactersRepository>()
    private var getMovies = mock<GetCharacters>()

    @Before
    fun setup() {
        getMovies = mock()
        viewModel = CharacterViewModel(getMovies)
    }

    @Test
    fun `should emit error on get movies lookup failure`() =
        coroutinesRule.dispatcher.runBlockingTest {

            val expectedMovies = Success(CharactersEntity.empty().toCharacters().toCharactersView())

            val channel = Channel<Success<CharactersView>>()
            val flow = channel.consumeAsFlow()

            doReturn(flow)
                .whenever(repository)
                .getCharacters(null, false)

            val job = launch {
                channel.send(expectedMovies)
                channel.close(IOException())
            }

            viewModel.getCharacters(false)

            viewModel.characters.value shouldBeEqualTo expectedMovies.data
            job.cancel()
        }
}