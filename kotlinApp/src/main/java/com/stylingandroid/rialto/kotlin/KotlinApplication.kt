package com.stylingandroid.rialto.kotlin

import android.app.Application
import android.text.style.UnderlineSpan
import com.stylingandroid.rialto.Registry
import com.stylingandroid.rialto.RialtoRegistry

class KotlinApplication @JvmOverloads constructor(
    registry: RialtoRegistry = Registry()
) : Application(), RialtoRegistry by registry {

    override fun onCreate() {
        super.onCreate()
        registerSpanFactory("format", "underline") { UnderlineSpan() }
    }
}
