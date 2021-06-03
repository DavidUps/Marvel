package com.davidups.marvel.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.davidups.marvel.core.database.typeconverters.CharacterConverter
import com.davidups.marvel.data.models.data.Characters
import com.davidups.marvel.extensions.empty

@Entity
data class CharactersEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    @TypeConverters(CharacterConverter::class)
    var results: MutableList<CharacterEntity>?
) {
    companion object {
        fun empty() =
            CharactersEntity(
                Int.empty(),
                Int.empty(),
                Int.empty(),
                Int.empty(),
                Int.empty(),
                mutableListOf()
            )
    }

    fun toCharacters() =
        Characters(offset, limit, total, count, results?.map { it.toCharacter() }?.toMutableList())
}
