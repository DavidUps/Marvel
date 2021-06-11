package com.davidups.characters.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.davidups.characters.core.dao.CharactersDAO
import com.davidups.characters.core.database.typeconverters.CharacterConverter
import com.davidups.characters.data.models.entity.CharactersEntity

@Database(entities = [CharactersEntity::class], version = 1)
@TypeConverters(CharacterConverter::class)
abstract class CharactersDatabase : RoomDatabase() {

    abstract fun characterDao(): CharactersDAO
}
