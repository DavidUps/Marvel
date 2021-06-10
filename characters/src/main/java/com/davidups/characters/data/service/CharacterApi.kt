package com.davidups.characters.data.service

import com.davidups.characters.data.models.entity.CharacterEntity
import com.davidups.characters.data.models.entity.CharactersEntity
import com.davidups.core.platform.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface CharacterApi {

    companion object {
        const val CHARACTERS = "/v1/public/characters"
        const val CHARACTER = "/v1/public/characters/{characterId}"
    }

    @GET(CHARACTERS)
    suspend fun getCharacters(
        @Query("limit") limit: Int? = 10,
        @Query("offset") offset: Int? = 0
    ): Response<BaseResponse<CharactersEntity>>

    @GET(CHARACTER)
    suspend fun getCharacter(@Path("characterId") id: String?): Response<BaseResponse<CharacterEntity>>
}
