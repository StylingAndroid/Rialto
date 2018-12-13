package com.stylingandroid.rialto.app

import android.graphics.Typeface
import android.os.Bundle
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.widget.TextView
import com.stylingandroid.rialto.format.getFormattedText
import kotlinx.android.synthetic.androidx.activity_main.*

class MainActivity : RialtoActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerSpanFactory("format", "bold") { StyleSpan(Typeface.BOLD) }
        registerSpanFactory("format", "italic") { StyleSpan(Typeface.ITALIC) }
        registerSpanFactory("format", "bold_underline") { StyleSpan(Typeface.BOLD) }
        registerSpanFactory("format", "bold_underline") { UnderlineSpan() }
        setContentView(R.layout.activity_main)

        format_string.setText(
                resources.getFormattedText(R.string.formatted_italic, "formatted"),
                TextView.BufferType.SPANNABLE
        )
    }
}
