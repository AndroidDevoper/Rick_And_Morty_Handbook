package com.example.rickandmortycharacters.ui.locations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmortycharacters.databinding.FragmentLocationBinding
import com.example.rickandmortycharacters.model.location.Location


class FragmentLocation : Fragment() {

    private lateinit var binding: FragmentLocationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocationBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        val location = arguments?.get("location") as Location
        binding.fragmentLocationTvName.text = location.name
        binding.fragmentLocationTvIsType.text = location.type
        binding.fragmentLocationTvIsDimension.text = location.dimension
        return binding.root
    }

}