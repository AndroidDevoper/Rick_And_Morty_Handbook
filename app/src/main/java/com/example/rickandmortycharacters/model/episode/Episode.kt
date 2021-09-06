package com.example.rickandmortycharacters.model.episode

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Episode (@Json(name = "name") val name: String,
                    @Json(name = "air_date") val air_date: String,
                    @Json(name = "episode") val episode: String): Parcelable