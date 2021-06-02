package com.davidups.marvel.features.characters.views.fragments

import android.os.Bundle
import com.davidups.marvel.R
import com.davidups.marvel.core.platform.BaseFragment
import com.davidups.marvel.databinding.FragmentStartBinding

class Fragment : BaseFragment(R.layout.fragment_start) {

    val binding by viewBinding(FragmentStartBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
