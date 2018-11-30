package com.stylingandroid.rialto.app

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.stylingandroid.rialto.BaseCoroutineLifecycleHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job

class CoroutineLifecycleHandler @JvmOverloads constructor(
        lifecycleOwner: LifecycleOwner,
        dispatcher: CoroutineDispatcher = Main
) : BaseCoroutineLifecycleHandler(dispatcher), LifecycleObserver {

    init {
        lifecycleOwner.lifecycle.addObserver(this)
        job = Job()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        job.cancel()
    }
}
