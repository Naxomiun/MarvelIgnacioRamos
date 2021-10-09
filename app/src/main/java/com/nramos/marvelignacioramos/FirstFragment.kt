package com.nramos.marvelignacioramos

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.nramos.marvelignacioramos.adapters.HeroAdapter
import com.nramos.marvelignacioramos.databinding.FragmentFirstBinding
import com.nramos.marvelignacioramos.delegates.collectInLifeCycle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null //TODO futurible: change to delegates
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    private val heroAdapter by lazy {
        HeroAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
      _binding = FragmentFirstBinding.inflate(inflater, container, false)

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
        viewModel.state.collectInLifeCycle(viewLifecycleOwner, ::renderState)
    }

    private fun renderState(state : State) {
        when(state) {
            is Error -> {

            }
            is Loaded -> configLoadedState(state)
            is Loading -> {

            }
        }
    }

    private fun configLoadedState(state : Loaded) = with(state) {
        heroAdapter.submitList(this.heroes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}