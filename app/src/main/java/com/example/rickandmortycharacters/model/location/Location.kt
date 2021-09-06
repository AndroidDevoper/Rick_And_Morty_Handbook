package com.example.rickandmortycharacters.model.location

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Location (@Json(name = "name") val name: String,
                     @Json(name = "type") val type: String,
                     @Json(name = "dimension") val dimension: String): Parcelable