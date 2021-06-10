package com.davidups.characters.data.models.data

import com.davidups.characters.data.models.entity.CharacterEntity
import com.davidups.characters.data.models.view.CharacterView
import com.davidups.core.extensions.orEmpty

data class Character(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val resourceURI: String?,
    val characterImage: CharacterThumbnail?
) {

    fun toCharacterEntity() = CharacterEntity(id, name, description, modified, resourceURI, characterImage?.toCharacterThumbnailEntity())
    fun toCharacterView() = CharacterView(
        id.orEmpty(),
        name.orEmpty(),
        description.orEmpty(),
        modified,
        resourceURI.orEmpty(),
        characterImage?.image().orEmpty()
    )
}
