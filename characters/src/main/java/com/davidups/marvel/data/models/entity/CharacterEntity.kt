package com.davidups.marvel.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.davidups.marvel.data.models.data.Character
import com.davidups.marvel.extensions.empty
import java.util.Date

@Entity
data class CharacterEntity(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: Date?,
    val resourceURI: String?
) {
    companion object {
        fun empty() =
            CharacterEntity(Int.empty(), String.empty(), String.empty(), null, String.empty())
    }

    fun toCharacter() = Character(id, name, description, modified, resourceURI)
}
