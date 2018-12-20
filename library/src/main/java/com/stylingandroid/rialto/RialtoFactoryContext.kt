package com.stylingandroid.rialto

import android.content.Context
import android.content.ContextWrapper

internal class RialtoFactoryContext(
    base: Context,
    private val registry: RialtoRegistry
) : ContextWrapper(base), RialtoRegistry by registry {

    override fun registerSpanFactory(key: String, value: String, creator: () -> Any) {
        registry.registerSpanFactory(key, value, creator)
    }
}
