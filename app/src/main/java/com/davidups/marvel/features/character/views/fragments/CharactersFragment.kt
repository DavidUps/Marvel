package com.davidups.marvel.features.character.views.fragments

import android.os.Bundle
import com.davidups.marvel.marvel.R
import com.davidups.marvel.core.platform.BaseFragment
import com.davidups.marvel.core.platform.viewBinding.viewBinding
import com.davidups.marvel.features.character.views.viewmodel.CharacterViewModel
import com.davidups.marvel.marvel.databinding.CharactersFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class CharactersFragment : BaseFragment(R.layout.characters_fragment) {

    val binding by viewBinding(CharactersFragmentBinding::bind)
    val characterViewModel by viewModel<CharacterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        characterViewModel.getCharacters()
    }
}
