package com.davidups.marvel.data.models.data

import com.davidups.marvel.data.models.entity.CharacterEntity
import com.davidups.marvel.data.models.view.CharacterView
import com.davidups.marvel.extensions.orEmpty
import java.util.Date

data class Character(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: Date?,
    val resourceURI: String?
) {

    fun toCharacterEntity() = CharacterEntity(id, name, description, modified, resourceURI)
    fun toCharacterView() = CharacterView(
        id.orEmpty(),
        name.orEmpty(),
        description.orEmpty(),
        modified,
        resourceURI.orEmpty()
    )

}
