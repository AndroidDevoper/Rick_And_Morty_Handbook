package com.example.rickandmortycharacters.ui.characters

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.rickandmortycharacters.databinding.FragmentCharacterBinding
import com.example.rickandmortycharacters.model.character.Character
import com.squareup.picasso.Picasso

class FragmentCharacter : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCharacterBinding.inflate(layoutInflater)
        val character = arguments?.get("character") as Character
        binding.fragmentCharacterTvName.text = character.name
        binding.fragmentCharacterTvIsStatus.text = character.status
        binding.fragmentCharacterTvIsSpecies.text = character.species
        binding.fragmentCharacterTvIsGender.text = character.gender
        binding.fragmentCharacterTvIsLocation.text = character.location.name
        Picasso.get().load(character.image).into(binding.fragmentCharacterImg)
        setHasOptionsMenu(true)
        return binding.root
    }
}