package com.davidups.characters.data.service

import retrofit2.Retrofit

class CharacterService(retrofit: Retrofit) : CharacterApi {

    private val characterApi by lazy { retrofit.create(CharacterApi::class.java) }

    override suspend fun getCharacters(limit: Int?, offset: Int?) = characterApi.getCharacters(limit, offset)

    override suspend fun getCharacter(id: String?) = characterApi.getCharacter(id)
}
