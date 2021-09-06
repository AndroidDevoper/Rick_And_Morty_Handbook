package com.example.rickandmortycharacters

import android.app.Application
import com.example.rickandmortycharacters.model.RetrofitApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class MyApplication: Application() {

    companion object {
        lateinit var API: RetrofitApi
    }

    override fun onCreate() {
        super.onCreate()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        API = retrofit.create(RetrofitApi::class.java)
    }

}