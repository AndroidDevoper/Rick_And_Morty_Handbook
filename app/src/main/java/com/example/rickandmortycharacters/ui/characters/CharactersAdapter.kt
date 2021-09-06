package com.example.rickandmortycharacters.ui.characters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.rickandmortycharacters.databinding.ItemCharacterBinding
import com.example.rickandmortycharacters.model.character.Character
import com.example.rickandmortycharacters.model.character.PageCharacter
import com.squareup.picasso.Picasso

class CharactersAdapter(private val inflater: LayoutInflater,
                        private val callback: CallBack): BaseAdapter() {

    interface CallBack {
        fun onNextPage(num: Int)
        fun onCharacterSelect(character: Character)
    }

    private var listPages: ArrayList<PageCharacter> = ArrayList()
    private var listCharacters: ArrayList<Character> = ArrayList()


    fun addPage(pageCharactersData: PageCharactersData) {
        listPages = pageCharactersData.listPages
        listCharacters = pageCharactersData.listCharacters
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return listCharacters.size
    }

    override fun getItem(p0: Int): Any {
        return listCharacters[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding = ItemCharacterBinding.inflate(inflater, p2, false)
        binding.itemCharacterTvIsName.text = listCharacters[p0].name
        binding.itemCharacterTvIsSpecies.text = listCharacters[p0].species
        binding.itemCharacterTvIsStatus.text = listCharacters[p0].status
        binding.itemCharacterTvIsGender.text = listCharacters[p0].gender
        binding.root.setOnClickListener(View.OnClickListener {
            callback.onCharacterSelect(listCharacters[p0])
        })
        Picasso.get()
            .load(listCharacters[p0].image)
            .into(binding.itemCharacterImg)
        return binding.root
    }

    override fun getItemViewType(position: Int): Int {
        if (position == listCharacters.size - 4 && listPages.size < listPages[0].info.pages)
            callback.onNextPage(listPages.size + 1)
        return super.getItemViewType(position)
    }



}