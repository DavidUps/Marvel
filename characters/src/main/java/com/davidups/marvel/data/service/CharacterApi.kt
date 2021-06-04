package com.davidups.marvel.data.service

import com.davidups.marvel.platform.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface CharacterApi {

    companion object {
        const val CHARACTERS = "/v1/public/characters"
        const val CHARACTER = "/v1/public/characters/{characterId}"
    }

    @GET(CHARACTERS)
    suspend fun getCharacters(): Response<BaseResponse>

    @GET(CHARACTER)
    suspend fun getCharacter(@Path("characterId") id: String?): Response<BaseResponse>

}