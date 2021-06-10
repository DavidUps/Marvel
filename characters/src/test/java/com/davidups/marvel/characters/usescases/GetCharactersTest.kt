package com.davidups.marvel.characters.usescases

import com.davidups.marvel.characters.UnitTest
import com.davidups.marvel.data.models.entity.CharactersEntity
import com.davidups.marvel.data.models.view.CharactersView
import com.davidups.marvel.domain.datasource.CharactersDataSourceImp
import com.davidups.marvel.domain.repository.CharactersRepository
import com.davidups.marvel.domain.repository.CharactersRepositoryImp
import com.davidups.marvel.domain.usecases.GetCharacters
import com.davidups.marvel.functional.Success
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

class GetCharactersTest : UnitTest() {

    private lateinit var repository: CharactersRepository
    private lateinit var getCharacters: GetCharacters

    @Test
    fun `should get movies on success`() = runBlocking {

        val characters = CharactersEntity.empty()

        val moviesDataSource = mock<CharactersDataSourceImp> {
            onBlocking { getCharacters(null, false) } doReturn flow { emit(Success(characters.toCharacters().toCharactersView())) }
        }

        repository = CharactersRepositoryImp(moviesDataSource)

        getCharacters = GetCharacters(repository)

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