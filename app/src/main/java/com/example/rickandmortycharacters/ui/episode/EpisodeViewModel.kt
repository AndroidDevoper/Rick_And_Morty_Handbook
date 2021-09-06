package com.example.rickandmortycharacters.ui.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortycharacters.RxManager
import com.example.rickandmortycharacters.model.episode.PageEpisode

class EpisodeViewModel : ViewModel() {

    private var data = PageEpisodesData()

    private val _isLoading = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isLoading: LiveData<Boolean> = _isLoading

    private val rx = RxManager(object: RxManager.PageEpisodeListener {
        override fun onLoad(page: PageEpisode) {
            data.addDate(page)
            _pageData.value = data
            _isLoading.value = false
        }
    })

    private val _pageData = MutableLiveData<PageEpisodesData>().apply {
        if (data.listPages.size == 0)
            loadPage(0)
    }
    val pageData: LiveData<PageEpisodesData> = _pageData


    fun loadPage(num: Int) {
        if (_isLoading.value == false) {
            _isLoading.value = true
            rx.getPageEpisode(num)
        }
    }

    fun refresh() {
        loadPage(0)
        data = PageEpisodesData()
    }

    override fun onCleared() {
        super.onCleared()
        rx.detach()
    }
}