package net.fitken.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

open class BaseViewModel : ViewModel() {

    private val mJob: Job = Job()

    fun executeTask(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(mJob, CoroutineStart.DEFAULT, block)
    }

    inline fun <T> executeLiveDataTask(crossinline block: suspend () -> LiveData<T>): LiveData<T> {
        return liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emitSource(block())
        }
    }

    override fun onCleared() {
        mJob.cancel()
        viewModelScope.cancel()
        super.onCleared()
    }
}