package com.davidups.marvel.features.character.views.fragments

import android.os.Bundle
import com.davidups.marvel.marvel.R
import com.davidups.marvel.core.platform.BaseFragment
import com.davidups.marvel.core.platform.viewBinding.viewBinding
import com.davidups.marvel.marvel.databinding.CharactersFragmentBinding

class CharactersFragment : BaseFragment(R.layout.characters_fragment) {

    val binding by viewBinding(CharactersFragmentBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
