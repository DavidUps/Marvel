package com.davidups.marvel.data.models.view

import com.davidups.marvel.data.models.data.Characters
import com.davidups.marvel.extensions.empty

data class CharactersView(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: MutableList<CharacterView>
) {
    companion object {
        fun empty() =
            CharactersView(Int.empty(), Int.empty(), Int.empty(), Int.empty(), mutableListOf())
    }

    fun toCharacters() =
        Characters(offset, limit, total, count, results.map { it.toCharacter() }.toMutableList())
}
