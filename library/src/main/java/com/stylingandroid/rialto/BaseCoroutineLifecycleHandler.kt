package com.stylingandroid.rialto

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseCoroutineLifecycleHandler(
    private val dispatcher: CoroutineDispatcher
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + job

    protected lateinit var job: Job
}
