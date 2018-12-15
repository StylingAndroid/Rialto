package com.stylingandroid.rialto.app

import android.app.Application
import android.text.style.UnderlineSpan
import com.stylingandroid.rialto.Registry
import com.stylingandroid.rialto.RialtoRegistry
import com.stylingandroid.rialto.font.FontRegistrar

class KotlinApplication @JvmOverloads constructor(
    registry: RialtoRegistry = Registry()
) : Application(), RialtoRegistry by registry {

    override fun onCreate() {
        super.onCreate()
        registerSpanFactory("format", "underline") { UnderlineSpan() }
        FontRegistrar(this).registerFonts(this, "font", R.array.preloaded_fonts)
    }
}
