package com.example.rickandmortycharacters.ui.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortycharacters.RxManager
import com.example.rickandmortycharacters.model.location.PageLocation

class LocationsViewModel : ViewModel() {

    private var data = PageLocationsData()

    private val _isLoading = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isLoading: LiveData<Boolean> = _isLoading

    private val rx = RxManager(object: RxManager.PageLocationsListener {
        override fun onLoad(page: PageLocation) {
            data.addDate(page)
            _pageData.value = data
            _isLoading.value = false
        }
    })

    private val _pageData = MutableLiveData<PageLocationsData>().apply {
        if (data.listPages.size == 0)
            loadPage(0)
    }
    val pageData: LiveData<PageLocationsData> = _pageData


    fun loadPage(num: Int) {
        if (_isLoading.value == false) {
            _isLoading.value = true
            rx.getPageLocation(num)
        }
    }

    fun refresh() {
        loadPage((0..data.listPages[0].info.pages).random())
        data = PageLocationsData()
    }

    override fun onCleared() {
        super.onCleared()
        rx.detach()
    }

}