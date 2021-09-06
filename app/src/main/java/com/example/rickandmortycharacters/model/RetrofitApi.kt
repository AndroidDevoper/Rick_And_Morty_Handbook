package com.example.rickandmortycharacters.model

import com.example.rickandmortycharacters.model.character.PageCharacter
import com.example.rickandmortycharacters.model.episode.PageEpisode
import com.example.rickandmortycharacters.model.location.PageLocation
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("character?")
    fun getPageCharacter(@Query("page") page: String): Single<PageCharacter>

    @GET("location?")
    fun getPageLocation(@Query("page") page: String): Single<PageLocation>

    @GET("episode?")
    fun getPageEpisode(@Query("page") page: String): Single<PageEpisode>

}