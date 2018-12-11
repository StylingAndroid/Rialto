package com.stylingandroid.rialto.app

import android.graphics.Typeface
import android.os.Bundle
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import com.stylingandroid.rialto.app.RialtoActivity

class MainActivity : RialtoActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerSpanFactory("format", "bold") { StyleSpan(Typeface.BOLD) }
        registerSpanFactory("format", "italic") { StyleSpan(Typeface.ITALIC) }
        registerSpanFactory("format", "bold_underline") { StyleSpan(Typeface.BOLD) }
        registerSpanFactory("format", "bold_underline") { UnderlineSpan() }
        setContentView(R.layout.activity_main)
    }
}
