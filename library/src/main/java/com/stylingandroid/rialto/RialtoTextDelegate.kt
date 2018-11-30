package com.stylingandroid.rialto

import android.widget.TextView

interface RialtoTextDelegate {

    fun setText(
        text: CharSequence?,
        type: TextView.BufferType?,
        setter: (text: CharSequence?, type: TextView.BufferType?) -> Unit
    )

    var doFormatting: Boolean
}
