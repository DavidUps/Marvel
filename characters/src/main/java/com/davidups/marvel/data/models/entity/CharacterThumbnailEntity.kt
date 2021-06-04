package com.davidups.marvel.data.models.entity

import com.davidups.marvel.data.models.data.CharacterThumbnail
import com.davidups.marvel.extensions.empty

data class CharacterThumbnailEntity(val path: String?, val extension: String?, val image: String?) {

    companion object {
        fun empty() = CharacterThumbnailEntity(String.empty(), String.empty(), String.empty())
    }

    fun toCharacterThumbnail() = CharacterThumbnail(path, extension, image)

}
