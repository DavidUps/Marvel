package com.davidups.marvel.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.davidups.marvel.core.dao.CharactersDAO
import com.davidups.marvel.core.database.typeconverters.CharacterConverter
import com.davidups.marvel.data.models.entity.CharactersEntity

@Database(entities = [CharactersEntity::class], version = 1)
@TypeConverters(CharacterConverter::class)
abstract class CharactersDatabase : RoomDatabase() {

    abstract fun characterDao(): CharactersDAO
}
