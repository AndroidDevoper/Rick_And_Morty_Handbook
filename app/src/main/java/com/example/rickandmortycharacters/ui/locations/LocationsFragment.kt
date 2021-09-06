package com.example.rickandmortycharacters.ui.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rickandmortycharacters.R
import com.example.rickandmortycharacters.databinding.FragmentLocationsBinding
import com.example.rickandmortycharacters.model.location.Location

class LocationsFragment : Fragment() {

    private lateinit var viewModel: LocationsViewModel
    private var _binding: FragmentLocationsBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: LocationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(LocationsViewModel::class.java)
        _binding = FragmentLocationsBinding.inflate(inflater, container, false)
        initSwipeRefresh()
        initAdapter()
        initObservePageDate()
        initObserveLoading()
        return binding.root
    }

    private fun initSwipeRefresh() {
        binding.fragmentLocationsSwipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun initAdapter() {
        adapter = LocationsAdapter(layoutInflater, object: LocationsAdapter.CallBack {
            override fun onLocationSelect(location: Location) {
                val bundle = bundleOf(Pair("location", location))
                findNavController().navigate(R.id.action_navigation_locations_to_fragmentLocation, bundle)
            }

            override fun onNextPage(num: Int) {
                viewModel.loadPage(num)
            }
        })
        binding.fragmentLocationsListView.adapter = adapter
    }

    private fun initObservePageDate() {
        viewModel.pageData.observe(viewLifecycleOwner, {
            adapter.addPage(it)
        })
    }

    private fun initObserveLoading() {
        viewModel.isLoading.observe(viewLifecycleOwner, {
            binding.fragmentLocationsSwipeRefresh.isRefreshing = it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}