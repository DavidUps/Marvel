package com.davidups.core.characters.repository

import com.davidups.characters.data.models.entity.CharactersEntity
import com.davidups.characters.data.models.view.CharactersView
import com.davidups.characters.domain.datasource.CharactersDataSourceImp
import com.davidups.characters.domain.repository.CharactersRepositoryImp
import com.davidups.core.functional.Success
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

class CharactersRepositoryTest {

    @Test
    fun `should get movies on success`() = runBlocking {

        val characters = CharactersEntity.empty()

        val moviesDataSource = mock<CharactersDataSourceImp> {
            onBlocking {
                getCharacters(
                    null,
                    false
                )
            } doReturn flow { emit(Success(characters.toCharacters().toCharactersView())) }
        }

        val repository = CharactersRepositoryImp(moviesDataSource)

        val flow = repository.getCharacters(null, false)

        flow.collect { result ->
            result.`should be instance of`<Success<CharactersView>>()
            when (result) {
                is Success<CharactersView> -> {
                    result.data shouldBeEqualTo characters.toCharacters().toCharactersView()
                }
            }
        }
    }
}
