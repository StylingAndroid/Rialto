package com.stylingandroid.rialto

import android.content.Context
import android.content.ContextWrapper
import android.util.AttributeSet
import android.widget.TextView

internal class TextViewDelegate(
        context: Context,
        attrs: AttributeSet,
        private val textView: TextView,
        override var doFormatting: Boolean = true
) : RialtoTextDelegate {

    init {
        doFormatting =
                context.theme.obtainStyledAttributes(attrs, R.styleable.TextView, 0, 0).let { ta ->
                    try {
                        ta.getBoolean(R.styleable.TextView_annotationFormatting, true)
                    } finally {
                        ta.recycle()
                    }
                }
    }

    private val delegate: RialtoDelegate? = textView.context.findFactoryProvider()

    private fun Context.findFactoryProvider(): RialtoDelegate? {
        return when (this) {
            is RialtoDelegate -> this
            is ContextWrapper -> baseContext.findFactoryProvider()
            else -> null
        }
    }

    override fun setText(
            text: CharSequence?,
            type: TextView.BufferType?,
            setter: (CharSequence?, TextView.BufferType?) -> Unit) {
        if (textView.text?.isEmpty() == true && text?.isNotEmpty() == true) {
            setter(text, type)
        }

        if (doFormatting) {
            delegate?.processAnnotations(text)?.also { processedText ->
                setter(processedText, type)
            }

        }
    }
}
