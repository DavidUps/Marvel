package com.davidups.marvel.features.character.views.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.davidups.marvel.core.platform.BaseFragment
import com.davidups.marvel.core.platform.viewBinding.viewBinding
import com.davidups.marvel.data.models.view.CharactersView
import com.davidups.marvel.extensions.endless
import com.davidups.marvel.extensions.failure
import com.davidups.marvel.extensions.observe
import com.davidups.marvel.features.character.views.adapters.CharactersAdapter
import com.davidups.marvel.features.character.views.viewmodel.CharacterViewModel
import com.davidups.marvel.marvel.R
import com.davidups.marvel.marvel.databinding.CharactersFragmentBinding
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough
import org.koin.android.viewmodel.ext.android.viewModel

class CharactersFragment : BaseFragment(R.layout.characters_fragment) {

    private val binding by viewBinding(CharactersFragmentBinding::bind)
    private val characterViewModel by viewModel<CharacterViewModel>()

    private val charactersAdapter by lazy { CharactersAdapter() }
    private var rvEndListener = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialFadeThrough().apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }

        with(characterViewModel) {
            observe(characters, ::handleCharacters)
            observe(showSpinner, ::handleShowSpinner)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListeners()
        getData()
    }

    private fun initView() {
        postponeEnterTransition()
        requireView().doOnPreDraw { startPostponedEnterTransition() }

        binding.rvCharacters.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = charactersAdapter
        }
    }

    private fun initListeners() {
        binding.rvCharacters.endless(charactersAdapter.itemCount) {
            rvEndListener = false
            characterViewModel.getCharacters(true)
        }
        charactersAdapter.characterListener = { character, view ->

            exitTransition = MaterialElevationScale(false).apply {
                duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            }
            val characterCardDetailTransitionName =
                getString(R.string.character_card_detail_transition_name)
            val extras = FragmentNavigatorExtras(view to characterCardDetailTransitionName)
            val directions =
                CharactersFragmentDirections.actionCharactersFragmentToCharacterFragment(character)
            findNavController().navigate(directions, extras)
        }
    }

    private fun getData() {
        characterViewModel.getCharacters()
    }

    private fun handleCharacters(charactersView: CharactersView?) {
        charactersView?.let {
            rvEndListener = true
            val list = charactersAdapter.collection.toMutableList()
            list.addAll(it.results)
            charactersAdapter.collection = list
        }
    }
}
