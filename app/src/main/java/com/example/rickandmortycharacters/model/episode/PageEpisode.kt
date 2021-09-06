package com.example.rickandmortycharacters.model.episode

import com.example.rickandmortycharacters.model.PageInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageEpisode (@Json(name = "info") val info: PageInfo,
                        @Json(name = "results") val results: List<Episode>)