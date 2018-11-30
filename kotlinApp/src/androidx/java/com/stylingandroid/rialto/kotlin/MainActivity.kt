package com.stylingandroid.rialto.kotlin

import android.graphics.Typeface
import android.os.Bundle
import android.text.style.StyleSpan
import com.stylingandroid.rialto.app.RialtoActivity

class MainActivity : RialtoActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerSpanFactory("format", "bold") { StyleSpan(Typeface.BOLD) }
        registerSpanFactory("format", "italic") { StyleSpan(Typeface.ITALIC) }
        setContentView(R.layout.activity_main)
    }
}
