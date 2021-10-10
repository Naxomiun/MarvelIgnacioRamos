package com.nramos.marvelignacioramos.ui.screens.herolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nramos.marvelignacioramos.ui.screens.herodetail.FragmentHeroDetail.Companion.HERO_ID_BUNDLE_KEY
import com.nramos.marvelignacioramos.R
import com.nramos.marvelignacioramos.ui.screens.herolist.adapters.HeroAdapter
import com.nramos.marvelignacioramos.databinding.FragmentHeroListBinding
import com.nramos.marvelignacioramos.delegates.collectInLifeCycle
import com.nramos.marvelignacioramos.delegates.launchInLifeCycle
import com.nramos.marvelignacioramos.ui.gone
import com.nramos.marvelignacioramos.ui.screens.herolist.viewmodel.*
import com.nramos.marvelignacioramos.ui.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class FragmentHero : Fragment() {

    private var _binding: FragmentHeroListBinding? = null //TODO futurible: change to delegates
    private val binding get() = _binding!!

    private val viewModel: HeroListViewModel by viewModels()

    private val navController by lazy {
       findNavController()
    }

    private val heroAdapter by lazy {
        HeroAdapter { heroId ->
            viewModel.navigateToDetail(heroId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeroListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclers()
        setupObservers()
    }

    private fun setupRecyclers() = with(binding) {
        rvHeroList.adapter = heroAdapter
    }

    private fun setupObservers() {
        viewModel.state.collectInLifeCycle(viewLifecycleOwner, ::renderState) //Collect states
        viewModel.event.onEach(::launchEvent).launchInLifeCycle(viewLifecycleOwner) //Collect events
    }

    private fun launchEvent(event: Event) {
        when (event) {
            is NavigateToDetail -> loadHeroDetail(event.heroId)
        }
    }

    private fun renderState(state: State) {
        when (state) {
            is Error -> configErrorState(state)
            is Loaded -> configLoadedState(state)
            is Loading -> configLoadingState()
        }
    }

    private fun configLoadedState(state: Loaded) = with(binding) {
        heroAdapter.submitList(state.heroes)
        tvErrorMessage.gone()
        rvHeroList.visible()
        progressCircular.gone()
    }

    private fun configErrorState(state: Error) = with(binding) {
        tvErrorMessage.text = state.error.message
        tvErrorMessage.visible()
        rvHeroList.gone()
        progressCircular.gone()
    }

    private fun configLoadingState() = with(binding) {
        tvErrorMessage.gone()
        rvHeroList.gone()
        progressCircular.visible()
    }

    private fun loadHeroDetail(heroId: Int) {
        val bundle = Bundle().apply {
            putInt(HERO_ID_BUNDLE_KEY, heroId)
        }
        navController.navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}