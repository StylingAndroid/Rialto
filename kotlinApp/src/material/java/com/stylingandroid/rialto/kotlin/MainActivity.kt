package com.stylingandroid.rialto.kotlin

import android.graphics.Typeface
import android.os.Bundle
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import com.stylingandroid.rialto.RialtoDelegate
import com.stylingandroid.rialto.RialtoDelegateImpl

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
        setContentView(R.layout.activity_main)
    }
}
