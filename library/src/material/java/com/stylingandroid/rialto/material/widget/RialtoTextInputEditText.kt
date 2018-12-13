package com.stylingandroid.rialto.material.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.stylingandroid.rialto.RialtoTextDelegate
import com.stylingandroid.rialto.TextViewDelegate
import kotlinx.coroutines.CoroutineScope

class RialtoTextInputEditText @JvmOverloads constructor(
        context: Context,
        private val attrs: AttributeSet? = null,
        defaultStyle: Int = 0
) : TextInputEditText(context, attrs, defaultStyle) {
    private lateinit var delegate: RialtoTextDelegate

    private var deferredText: Pair<CharSequence, TextView.BufferType>? = null

    var doFormatting: Boolean
        get() = delegate.doFormatting
        set(value) {
            delegate.doFormatting = value
        }

    private fun initialiseDelegate() {
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
                deferredText = text to type
            }
        }
    }
}
