package com.davidups.marvel.features.character.views.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.davidups.core.extensions.loadFromUrl
import com.davidups.marvel.core.platform.BaseFragment
import com.davidups.marvel.R
import com.davidups.marvel.core.platform.viewBinding.viewBinding
import com.davidups.marvel.databinding.CharacterFragmentBinding
import com.google.android.material.transition.MaterialContainerTransform
import kotlinx.android.synthetic.main.navigation_activity.toolbar
import kotlin.LazyThreadSafetyMode.NONE

class CharacterFragment : BaseFragment(R.layout.character_fragment) {

    private val binding by viewBinding(CharacterFragmentBinding::bind)
    private val arguments by navArgs<CharacterFragmentArgs>()

    private val character by lazy(NONE) { arguments.character }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().toolbar.title = character?.name
        binding.apply {
            ivCharacter.loadFromUrl(character?.image.orEmpty())
            tvDescription.text = character?.description
        }
    }
}
