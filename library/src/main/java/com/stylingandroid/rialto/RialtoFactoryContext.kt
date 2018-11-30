package com.stylingandroid.rialto

import android.content.Context
import android.content.ContextWrapper

internal class RialtoFactoryContext(
    base: Context, val delegate: RialtoDelegate
) : ContextWrapper(base), RialtoDelegate by delegate {

    override fun registerSpanFactory(key: String, value: String, creator: () -> Any) {
        delegate.registerSpanFactory(key, value, creator)
    }
}
