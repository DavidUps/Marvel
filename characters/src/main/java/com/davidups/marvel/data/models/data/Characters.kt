package com.davidups.marvel.data.models.data

import com.davidups.marvel.data.models.entity.CharactersEntity
import com.davidups.marvel.data.models.view.CharactersView
import com.davidups.marvel.extensions.orEmpty

data class Characters(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: MutableList<Character>?,
) {

    fun toCharactersEntity() =
        CharactersEntity(
            null,
            offset,
            limit,
            total,
            count,
            mutableListOf()
        )

    fun toCharactersView() =
        CharactersView(
            offset.orEmpty(),
            limit.orEmpty(),
            total.orEmpty(),
            count.orEmpty(),
            results?.map { it.toCharacterView() }?.toMutableList().orEmpty()
        )
}
