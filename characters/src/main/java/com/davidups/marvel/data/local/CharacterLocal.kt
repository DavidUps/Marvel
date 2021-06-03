package com.davidups.marvel.data.local

import com.davidups.marvel.core.database.CharactersDatabase
import com.davidups.marvel.data.models.data.Characters
import com.davidups.marvel.data.models.entity.CharactersEntity

class CharacterLocal(database: CharactersDatabase): CharacterDB {

    private val characterDao by lazy {
        database.characterDao()
    }

    override fun putCharacters(characters: CharactersEntity) = characterDao.insert(characters)

    override fun getCharacters() = characterDao.getCharacters()

}
