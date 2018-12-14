package com.stylingandroid.rialto.support.widget

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.widget.TextView
import com.stylingandroid.rialto.TextViewDelegate

class RialtoTextView @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defaultStyle: Int = 0
) : AppCompatTextView(context, attrs, defaultStyle) {

    private lateinit var delegate: TextViewDelegate

    private var deferredText: Pair<CharSequence, TextView.BufferType>? = null

    var doFormatting: Boolean
        get() = delegate.doFormatting
        set(value) {
            delegate.doFormatting = value
        }

    fun initialiseDelegate() {
        attrs?.also {
            delegate = TextViewDelegate(context, it, this)
            deferredText?.also { (text, type) ->
                setText(text, type)
            }
            deferredText = null
        }
    }

    override fun setText(text: CharSequence?, type: TextView.BufferType?) {
        /*
         * setText() will be called from the base class constructor, so we need to defer
         * actually setting the text until we have created the Delegate
         */
        if (::delegate.isInitialized) {
            delegate.setText(text, type) { newText, newType ->
                super.setText(newText, newType)
            }
        } else {
            if (text != null && type != null) {
                super.setText(text, type)
                deferredText = text to type
            }
        }
    }
}
