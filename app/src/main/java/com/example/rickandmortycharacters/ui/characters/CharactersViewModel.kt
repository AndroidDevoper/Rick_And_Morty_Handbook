package com.example.rickandmortycharacters.ui.characters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortycharacters.RxManager
import com.example.rickandmortycharacters.model.character.PageCharacter


class CharactersViewModel(application: Application) : AndroidViewModel(application) {

    private var data = PageCharactersData()

    private val rx = RxManager(object :RxManager.PageCharacterListener {
        override fun onLoad(page: PageCharacter) {
            data.addDate(page)
            _pageDate.value = data
            _isLoading.value = false
        }
    })

    private val _isLoading = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isLoading: LiveData<Boolean> = _isLoading

    private val _pageDate = MutableLiveData<PageCharactersData>().apply {
        if (data.listPages.size == 0)
            loadPage(0)
    }
    val pageCharactersData: LiveData<PageCharactersData> = _pageDate

    fun loadPage(num: Int) {
        if (_isLoading.value == false) {
            _isLoading.value = true
            rx.getPageCharacter(num)
        }
    }

    fun refresh() {
        loadPage((0..data.listPages[0].info.pages).random())
        data = PageCharactersData()
    }
    override fun onCleared() {
        super.onCleared()
        rx.detach()
    }
}