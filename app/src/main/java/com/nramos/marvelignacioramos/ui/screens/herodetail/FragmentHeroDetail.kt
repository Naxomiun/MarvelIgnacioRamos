package com.nramos.marvelignacioramos.ui.screens.herodetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import coil.load
import com.nramos.marvelignacioramos.databinding.FragmentHeroDetailBinding
import com.nramos.marvelignacioramos.delegates.collectInLifeCycle
import com.nramos.marvelignacioramos.ui.gone
import com.nramos.marvelignacioramos.ui.screens.herodetail.viewmodel.*
import com.nramos.marvelignacioramos.ui.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentHeroDetail : Fragment() {

    companion object {
        val HERO_ID_BUNDLE_KEY = "heroId"
    }

    private var _binding: FragmentHeroDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HeroDetailViewModel by viewModels()

    private val heroId : Int
        get() = arguments?.getInt(HERO_ID_BUNDLE_KEY) ?: 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeroDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.state.collectInLifeCycle(viewLifecycleOwner, ::renderState)
        viewModel.getCharacterData(heroId)
    }

    private fun renderState(state: State) {
        when (state) {
            is Error -> configErrorState(state)
            is Loaded -> configLoadedState(state)
            is Loading -> configLoadingState()
        }
    }

    private fun configLoadedState(state: Loaded) = with(binding) {
        ivHeroPreview.load(state.hero.thumbnail)
        tvHeroName.text = state.hero.name
        tvHeroComics.text = "Comics: ${state.hero.comics}" //TODO futurible: replace it with Strings
        tvHeroSeries.text = "Series: ${state.hero.series}"
        tvHeroStories.text = "Stories: ${state.hero.series}"
        tvHeroDescription.text = state.hero.description
        groupHeroDetail.visible()
        tvErrorMessage.gone()
        progressCircular.gone()
    }

    private fun configErrorState(state: Error) = with(binding) {
        tvErrorMessage.text = state.error.message
        tvErrorMessage.visible()
        groupHeroDetail.gone()
        progressCircular.gone()
    }

    private fun configLoadingState() = with(binding) {
        tvErrorMessage.gone()
        groupHeroDetail.gone()
        progressCircular.visible()
    }

}