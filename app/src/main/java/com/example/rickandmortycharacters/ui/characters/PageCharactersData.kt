package com.example.rickandmortycharacters.ui.characters

import com.example.rickandmortycharacters.model.character.Character
import com.example.rickandmortycharacters.model.character.PageCharacter

class PageCharactersData {
    val listPages: ArrayList<PageCharacter> = ArrayList()
    val listCharacters: ArrayList<Character> = ArrayList()

    fun addDate(page: PageCharacter) {
        listPages.add(page)
        for(character in page.results) {
            listCharacters.add(character)
        }
    }
}