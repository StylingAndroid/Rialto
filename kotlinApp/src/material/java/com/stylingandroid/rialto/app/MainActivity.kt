package com.stylingandroid.rialto.app

import android.graphics.Typeface
import android.os.Bundle
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AppCompatActivity
import com.stylingandroid.rialto.RialtoDelegate
import com.stylingandroid.rialto.RialtoDelegateImpl
import com.stylingandroid.rialto.format.getFormattedText
import kotlinx.android.synthetic.material.activity_main.*

class MainActivity : AppCompatActivity(), RialtoDelegate {
    private lateinit var delegate: RialtoDelegate

    override fun registerSpanFactory(key: String, value: String, creator: () -> Any) {
        delegate.registerSpanFactory(key, value, creator)
    }

    override fun processAnnotations(text: CharSequence?): CharSequence? =
            delegate.processAnnotations(text)

    override fun onCreate(savedInstanceState: Bundle?) {
        delegate = RialtoDelegateImpl(this)

        super.onCreate(savedInstanceState)
        registerSpanFactory("format", "bold") { StyleSpan(Typeface.BOLD) }
        registerSpanFactory("format", "italic") { StyleSpan(Typeface.ITALIC) }
        registerSpanFactory("format", "bold_underline") { StyleSpan(Typeface.BOLD) }
        registerSpanFactory("format", "bold_underline") { UnderlineSpan() }
        setContentView(R.layout.activity_main)

        format_string.text = resources.getFormattedText(R.string.formatted_italic, "formatted")
    }
}
