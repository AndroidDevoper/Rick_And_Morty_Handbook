package com.example.rickandmortycharacters.ui.episode

import com.example.rickandmortycharacters.model.episode.Episode
import com.example.rickandmortycharacters.model.episode.PageEpisode

class PageEpisodesData {
    val listPages: ArrayList<PageEpisode> = ArrayList()
    val listEpisode: ArrayList<Episode> = ArrayList()

    fun addDate(page: PageEpisode) {
        listPages.add(page)
        for(episode in page.results) {
            listEpisode.add(episode)
        }
    }
}