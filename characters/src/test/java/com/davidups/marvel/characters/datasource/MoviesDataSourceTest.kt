package com.davidups.marvel.characters.datasource

import com.davidups.marvel.data.local.CharacterLocal
import com.davidups.marvel.data.models.entity.CharactersEntity
import com.davidups.marvel.data.models.view.CharactersView
import com.davidups.marvel.data.service.CharacterApi
import com.davidups.marvel.data.service.CharacterService
import com.davidups.marvel.domain.datasource.CharactersDataSourceImp
import com.davidups.marvel.domain.repository.CharactersRepositoryImp
import com.davidups.marvel.functional.Success
import com.davidups.marvel.platform.BaseResponse
import com.davidups.marvel.platform.NetworkHandler
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import retrofit2.Response

class MoviesDataSourceTest {

    @Test
    fun `should get characters on success`() = runBlocking {

        val movies = CharactersEntity.empty()
        val apiResponse = BaseResponse(0, "", "", "", "", movies, "")

        val moviesApi = mock<CharacterApi> {
            onBlocking { getCharacters() } doReturn Response.success(apiResponse)
        }

        moviesApi.getCharacters().body() shouldBeEqualTo movies

        val service = mock<CharacterService> {
            onBlocking { getCharacters() } doReturn Response.success(apiResponse)
        }

        val local = mock<CharacterLocal> {
            onBlocking { getCharacters() } doReturn movies
        }

        val dataSource = CharactersDataSourceImp(mock(), service, local)

        val flow = dataSource.getCharacters(null, false)

        flow.collect { result ->
            result.`should be instance of`<Success<CharactersView>>()
            when (result) {
                is Success<CharactersView> -> {
                    result.data shouldBeEqualTo movies.toCharacters().toCharactersView()
                }
            }
        }
    }
}