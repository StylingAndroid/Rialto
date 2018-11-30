package com.stylingandroid.rialto

import kotlinx.coroutines.CoroutineScope

interface CoroutineScopeConsumer {
    fun setCoroutineScope(coroutineScope: CoroutineScope)
}
