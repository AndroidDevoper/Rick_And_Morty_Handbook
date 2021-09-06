package com.example.rickandmortycharacters.model.character

import com.example.rickandmortycharacters.model.PageInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageCharacter(
    @Json(name = "info") val info: PageInfo,
    @Json(name = "results") val results: List<Character>
)
