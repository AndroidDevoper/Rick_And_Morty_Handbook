package com.example.rickandmortycharacters.model.location

import com.example.rickandmortycharacters.model.PageInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageLocation (
    @Json(name = "info") val info: PageInfo,
    @Json(name = "results") val results: List<Location>
)
