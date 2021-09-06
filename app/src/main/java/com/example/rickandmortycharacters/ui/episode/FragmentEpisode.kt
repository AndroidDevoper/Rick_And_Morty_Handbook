package com.example.rickandmortycharacters.ui.episode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmortycharacters.databinding.FragmentEpisodeBinding
import com.example.rickandmortycharacters.model.episode.Episode

class FragmentEpisode : Fragment() {

    private lateinit var binding: FragmentEpisodeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEpisodeBinding.inflate(layoutInflater, container, false)
        val episode = arguments?.get("episode") as Episode
        binding.fragmentEpisodeTvName.text = episode.name
        binding.fragmentEpisodeTvNum.text = episode.episode
        binding.fragmentEpisodeTvDate.text = episode.air_date
        setHasOptionsMenu(true)
        return binding.root
    }

}