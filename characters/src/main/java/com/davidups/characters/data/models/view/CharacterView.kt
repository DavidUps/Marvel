package com.davidups.characters.data.models.view

import android.os.Parcelable
import com.davidups.characters.data.models.data.Character
import com.davidups.characters.data.models.data.CharacterThumbnail
import com.davidups.core.extensions.empty
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterView(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String?,
    val resourceURI: String,
    val image: String
) : Parcelable {
    companion object {
        fun empty() =
            CharacterView(
                Int.empty(),
                String.empty(),
                String.empty(),
                null,
                String.empty(),
                String.empty()
            )
    }

    fun toCharacter() =
        Character(id, name, description, modified, resourceURI, CharacterThumbnail.thumbail(image))
}
