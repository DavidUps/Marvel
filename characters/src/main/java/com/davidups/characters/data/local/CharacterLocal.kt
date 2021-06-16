package com.davidups.core.data.local

import com.davidups.core.core.database.CharactersDatabase
import com.davidups.characters.data.models.entity.CharactersEntity

class CharacterLocal(database: CharactersDatabase) : CharacterDB {

    private val characterDao by lazy {
        database.characterDao()
    }

    override fun putCharacters(characters: CharactersEntity) = characterDao.insert(characters)

    override fun updateCharacters(characters: CharactersEntity) = characterDao.update(characters)

    override fun getCharacters() = characterDao.getCharacters()

    override fun getOffset() = characterDao.getOffset()
}
