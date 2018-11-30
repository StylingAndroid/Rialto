package com.stylingandroid.rialto.app

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import com.stylingandroid.rialto.BaseCoroutineLifecycleHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class CoroutineLifecycleHandler @JvmOverloads constructor(
    lifecycleOwner: LifecycleOwner,
    dispatcher: CoroutineDispatcher = Dispatchers.Main
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
