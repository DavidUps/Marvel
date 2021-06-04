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
    val offset: Double?,
    val limit: Double?,
    val total: Double?,
    val count: Double?,
    @TypeConverters(CharacterConverter::class)
    var results: MutableList<CharacterEntity>?
) {
    companion object {
        fun empty() =
            CharactersEntity(
                Int.empty(),
                Double.empty(),
                Double.empty(),
                Double.empty(),
                Double.empty(),
                mutableListOf()
            )
    }

    fun toCharacters() =
        Characters(offset?.toInt(), limit?.toInt(), total?.toInt(), count?.toInt(), results?.map { it.toCharacter() }?.toMutableList())
}
