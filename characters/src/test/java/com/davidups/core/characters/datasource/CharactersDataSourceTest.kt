package com.davidups.core.characters.datasource

import com.davidups.core.data.local.CharacterLocal
import com.davidups.characters.data.models.entity.CharactersEntity
import com.davidups.characters.data.models.view.CharactersView
import com.davidups.characters.data.service.CharacterApi
import com.davidups.characters.data.service.CharacterService
import com.davidups.characters.domain.datasource.CharactersDataSourceImp
import com.davidups.core.functional.Success
import com.davidups.core.platform.BaseResponse
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import retrofit2.Response

class CharactersDataSourceTest {

    @Test
    fun `should get characters on success`() = runBlocking {

        val characters = CharactersEntity.empty()
        val apiResponse = BaseResponse(0, "", "", "", "", characters, "")

        val charactersApi = mock<CharacterApi> {
            onBlocking { getCharacters() } doReturn Response.success(apiResponse)
        }

        charactersApi.getCharacters().body() shouldBeEqualTo apiResponse

        val service = mock<CharacterService> {
            onBlocking { getCharacters() } doReturn Response.success(apiResponse)
        }

        val local = mock<CharacterLocal> {
            onBlocking { getCharacters() } doReturn characters
        }

        val dataSource = CharactersDataSourceImp(mock(), service, local)

        val flow = dataSource.getCharacters(null, false)

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
