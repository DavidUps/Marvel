package com.davidups.marvel.data.models.entity

import androidx.room.Entity
import com.davidups.marvel.data.models.data.Character
import com.davidups.marvel.extensions.empty

@Entity
data class CharacterEntity(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val resourceURI: String?,
    val thumbnail: CharacterThumbnailEntity?
) {
    companion object {
        fun empty() =
            CharacterEntity(
                Int.empty(),
                String.empty(),
                String.empty(),
                null,
                String.empty(),
                CharacterThumbnailEntity.empty()
            )
    }

    fun toCharacter() =
        Character(id, name, description, modified, resourceURI, thumbnail?.toCharacterThumbnail())
}
