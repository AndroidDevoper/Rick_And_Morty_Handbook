package com.example.rickandmortycharacters.model.character

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Character(
    @Json(name = "name") val name: String,
    @Json(name = "species") val species: String,
    @Json(name = "status") val status: String,
    @Json(name = "gender") val gender: String,
    @Json(name = "image") val image: String,
    @Json(name = "location") val location: Location
): Parcelable {
    @JsonClass(generateAdapter = true)
    @Parcelize
    data class Location(@Json(name = "name") val name: String,
                        @Json(name = "url") val url: String) : Parcelable
}
