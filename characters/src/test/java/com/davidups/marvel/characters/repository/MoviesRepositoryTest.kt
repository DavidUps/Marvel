package com.davidups.marvel.characters.repository

import com.davidups.marvel.data.models.entity.CharactersEntity
import com.davidups.marvel.data.models.view.CharactersView
import com.davidups.marvel.domain.datasource.CharactersDataSourceImp
import com.davidups.marvel.domain.repository.CharactersRepositoryImp
import com.davidups.marvel.functional.Success
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

class MoviesRepositoryTest {

    @Test
    fun `should get movies on success`() = runBlocking {

        val movies = CharactersEntity.empty()

        val moviesDataSource = mock<CharactersDataSourceImp> {
            onBlocking {
                getCharacters(
                    null,
                    false
                )
            } doReturn flow { emit(Success(movies.toCharacters().toCharactersView())) }
        }

        val repository = CharactersRepositoryImp(moviesDataSource)

        val flow = repository.getCharacters(null, false)

        flow.collect { result ->
            result.`should be instance of`<Success<CharactersView>>()
            when (result) {
                is Success<CharactersView> -> {
                    result.data shouldBeEqualTo movies
                }
            }
        }
    }
}