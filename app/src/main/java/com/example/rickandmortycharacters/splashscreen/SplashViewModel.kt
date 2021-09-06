package com.example.rickandmortycharacters.splashscreen

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortycharacters.InternetAccess
import java.lang.Thread.sleep

@RequiresApi(Build.VERSION_CODES.M)
class SplashViewModel(application: Application) : AndroidViewModel(application) {
    private val ready = MutableLiveData<Boolean>()
    private val toast = MutableLiveData<String>()

    val isLoading: LiveData<Boolean> = ready
    val showToast: LiveData<String> = toast

    private val internetAccess: InternetAccess = InternetAccess(application.baseContext,
        object: InternetAccess.Callback {
            override fun onAccess(ch: Boolean) {
                if (ch) {
                    ready.value = true
                }
                else {
                    toast.value = "Network Error"
                    load()
                }
        }
    })

    init {
        internetAccess.scan()
    }

    private fun load() {
        sleep(1000)
        internetAccess.scan()
    }
}