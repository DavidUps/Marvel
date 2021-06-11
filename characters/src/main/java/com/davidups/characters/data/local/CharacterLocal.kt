package com.davidups.characters.data.local

import com.davidups.characters.data.models.entity.CharactersEntity
import com.davidups.characters.core.database.CharactersDatabase

class CharacterLocal(database: CharactersDatabase) : CharacterDB {

    private val characterDao by lazy {
        database.characterDao()
    }

    override fun putCharacters(characters: CharactersEntity) = characterDao.insert(characters)

    override fun updateCharacters(characters: CharactersEntity) = characterDao.update(characters)

    override fun getCharacters() = characterDao.getCharacters()
}
