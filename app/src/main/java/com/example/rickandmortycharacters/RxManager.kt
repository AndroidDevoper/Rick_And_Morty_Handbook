package com.example.rickandmortycharacters

import com.example.rickandmortycharacters.model.character.PageCharacter
import com.example.rickandmortycharacters.model.episode.PageEpisode
import com.example.rickandmortycharacters.model.location.PageLocation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RxManager(private val callback: Callback) {

    interface Callback {

    }

    interface PageCharacterListener: Callback {
        fun onLoad(page: PageCharacter)
    }

    interface PageLocationsListener: Callback {
        fun onLoad(page: PageLocation)
    }

    interface PageEpisodeListener: Callback {
        fun onLoad(page: PageEpisode)
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    fun getPageCharacter(page: Int) {
        callback as PageCharacterListener
        compositeDisposable.add(
            MyApplication.API.getPageCharacter(page.toString())
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onLoad(it)
            },
                {

                }))
    }

    fun getPageLocation(page: Int) {
        callback as PageLocationsListener
        compositeDisposable.add(
            MyApplication.API.getPageLocation(page.toString())
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onLoad(it)
            }, {

            }))
    }

    fun getPageEpisode(page: Int) {
        callback as PageEpisodeListener
        compositeDisposable.add(
            MyApplication.API.getPageEpisode(page.toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    callback.onLoad(it)
                }, {

                })
        )
    }

    fun detach() {
        compositeDisposable.clear()
    }
}