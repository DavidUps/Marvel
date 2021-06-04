package com.davidups.marvel.data.local

import com.davidups.marvel.data.models.entity.CharactersEntity

interface CharacterDB {
    fun getCharacters(): CharactersEntity?
    fun putCharacters(characters: CharactersEntity)
    fun updateCharacters(characters: CharactersEntity)
}