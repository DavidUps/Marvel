package com.davidups.characters.data.models.view

import com.davidups.characters.data.models.data.Characters
import com.davidups.core.extensions.empty

data class CharactersView(
    val offset: Int,
    val results: MutableList<CharacterView>
) {
    companion object {
        fun empty() =
            CharactersView(Int.empty(), mutableListOf())
    }

    fun toCharacters() =
        Characters(offset, results.map { it.toCharacter() }.toMutableList())
}
