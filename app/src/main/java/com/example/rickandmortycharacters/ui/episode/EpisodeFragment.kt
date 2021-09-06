package com.example.rickandmortycharacters.ui.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rickandmortycharacters.R
import com.example.rickandmortycharacters.databinding.FragmentEpisodesBinding
import com.example.rickandmortycharacters.model.episode.Episode

class EpisodeFragment : Fragment() {

    private lateinit var viewModel: EpisodeViewModel
    private var _binding: FragmentEpisodesBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: EpisodesAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel =
                ViewModelProvider(this).get(EpisodeViewModel::class.java)

        _binding = FragmentEpisodesBinding.inflate(inflater, container, false)
        initSwipeRefresh()
        initAdapter()
        initObservePageDate()
        initObserveLoading()
        return binding.root
    }

    private fun initSwipeRefresh() {
        binding.fragmentEpisodesSwipeLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun initAdapter() {
        adapter = EpisodesAdapter(layoutInflater, object: EpisodesAdapter.CallBack{
            override fun onNextPage(num: Int) {
                viewModel.loadPage(num)
            }

            override fun onEpisodeSelect(episode: Episode) {
                val bundle = bundleOf(Pair("episode", episode))
                findNavController().navigate(R.id.action_navigation_episode_to_fragmentEpisode, bundle)
            }

        })
        binding.fragmentEpisodesListView.adapter = adapter
    }

    private fun initObservePageDate() {
        viewModel.pageData.observe(viewLifecycleOwner, {
            adapter.addPage(it)
        })
    }

    private fun initObserveLoading() {
        viewModel.isLoading.observe(viewLifecycleOwner, {
            binding.fragmentEpisodesSwipeLayout.isRefreshing = it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}