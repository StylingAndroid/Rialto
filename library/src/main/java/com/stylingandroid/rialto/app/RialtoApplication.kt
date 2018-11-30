package com.stylingandroid.rialto.app

import android.app.Application
import com.stylingandroid.rialto.RialtoDelegate

class RialtoApplication : Application(), RialtoDelegate {

    lateinit var delegate: RialtoDelegate

    override fun registerSpanFactory(key: String, value: String, creator: () -> Any) {
        delegate.registerSpanFactory(key, value, creator)
    }

    override fun processAnnotations(text: CharSequence?): CharSequence? =
        delegate.processAnnotations(text)
}
