package com.denisgithuku.fluffy.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.denisgithuku.fluffy.network.DogApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private var coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _response = MutableLiveData<String>()
    val response: LiveData<String> = _response

    init {
        getDogData()
    }

    private fun getDogData() {
        coroutineScope.launch {
            val deferredProperties = DogApi.retrofitService.getDogListAsync()
            try {
                val listResult = deferredProperties.await()
                _response.value = "There are ${listResult.size} dog breeds"
            } catch (e: Throwable) {
                _response.value = "${e.message}"
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}