package com.davidups.marvel.core.dao

import androidx.room.Dao
import androidx.room.Query
import com.davidups.marvel.data.models.entity.CharactersEntity
import com.davidups.marvel.platform.BaseDao

@Dao
interface CharactersDAO : BaseDao<CharactersEntity> {

    @Query("SELECT * FROM CharactersEntity")
    fun getCharacters(): CharactersEntity

}
