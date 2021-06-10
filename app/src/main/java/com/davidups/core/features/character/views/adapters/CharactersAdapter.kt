package com.davidups.marvel.features.character.views.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davidups.marvel.data.models.view.CharacterView
import com.davidups.marvel.extensions.inflate
import com.davidups.marvel.extensions.loadFromUrl
import com.davidups.marvel.R
import com.davidups.marvel.databinding.CharacterItemBinding
import kotlin.properties.Delegates

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.EnvironmentHolder>() {

    internal var collection: List<CharacterView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }
    internal var characterListener: (CharacterView, View) -> Unit =
        { _: CharacterView, _: View -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EnvironmentHolder(parent.inflate(R.layout.character_item))

    override fun getItemCount(): Int = collection.size

    override fun onBindViewHolder(holder: EnvironmentHolder, position: Int) {
        holder.bind(collection[position], characterListener)
    }

    inner class EnvironmentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = CharacterItemBinding.bind(itemView)

        fun bind(item: CharacterView, characterListener: (CharacterView, View) -> Unit) {
            binding.cvItem.transitionName = String.format(
                itemView.context.getString(R.string.character_card_transition_name),
                item.id
            )
            binding.ivCharacter.loadFromUrl(item.image)
            binding.tvCharacterName.text = item.name
            binding.cvItem.setOnClickListener {
                characterListener(item, binding.cvItem)
            }
            binding.btnMoreInfo.setOnClickListener { characterListener(item, binding.cvItem) }
        }
    }
}
