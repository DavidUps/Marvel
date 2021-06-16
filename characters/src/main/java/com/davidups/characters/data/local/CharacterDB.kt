package com.davidups.core.data.local

import com.davidups.characters.data.models.entity.CharactersEntity

interface CharacterDB {
    fun getCharacters(): CharactersEntity?
    fun putCharacters(characters: CharactersEntity)
    fun updateCharacters(characters: CharactersEntity)
    fun getOffset(): Int?
}
