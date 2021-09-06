package com.example.rickandmortycharacters.ui.locations

import com.example.rickandmortycharacters.model.location.Location
import com.example.rickandmortycharacters.model.location.PageLocation

class PageLocationsData {
    val listPages: ArrayList<PageLocation> = ArrayList()
    val listLocations: ArrayList<Location> = ArrayList()

    fun addDate(page: PageLocation) {
        listPages.add(page)
        for(location in page.results) {
            listLocations.add(location)
        }
    }
}