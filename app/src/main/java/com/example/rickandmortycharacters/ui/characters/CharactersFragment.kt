package com.example.rickandmortycharacters.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rickandmortycharacters.R
import com.example.rickandmortycharacters.databinding.FragmentCharactersBinding
import com.example.rickandmortycharacters.model.character.Character

class CharactersFragment : Fragment() {

    private lateinit var viewModel: CharactersViewModel
    private var _binding: FragmentCharactersBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(CharactersViewModel::class.java)
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        initSwipeRefresh()
        initAdapter()
        initObservePageDate()
        initObserveLoading()
        return binding.root
    }

    private fun initSwipeRefresh() {
        binding.fragmentCharactersSwipeRefresh
            .setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun initAdapter() {
        adapter = CharactersAdapter(requireActivity().layoutInflater
            , object: CharactersAdapter.CallBack {
                override fun onNextPage(num: Int) {
                    viewModel.loadPage(num)
                }

                override fun onCharacterSelect(character: Character) {
                    val bundle = bundleOf(Pair("character", character))
                    findNavController().navigate(R.id.action_navigation_characters_to_fragmentCharacter, bundle)
                }
            })
        binding.fragmentCharectersListView.adapter = adapter
    }

    private fun initObservePageDate() {
        viewModel.pageCharactersData.observe(viewLifecycleOwner, Observer {
            adapter.addPage(it)
        })
    }

    private fun initObserveLoading() {
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.fragmentCharactersSwipeRefresh.isRefreshing = it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}