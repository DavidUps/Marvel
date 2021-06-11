package com.davidups.characters.usescases

import com.davidups.characters.data.models.entity.CharactersEntity
import com.davidups.characters.data.models.view.CharactersView
import com.davidups.characters.domain.datasource.CharactersDataSourceImp
import com.davidups.characters.domain.repository.CharactersRepository
import com.davidups.characters.domain.repository.CharactersRepositoryImp
import com.davidups.characters.domain.usecases.GetCharacters
import com.davidups.characters.UnitTest
import com.davidups.core.functional.Success
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
