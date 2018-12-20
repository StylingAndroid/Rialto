package com.stylingandroid.rialto

import android.text.Annotation
import android.text.SpannableStringBuilder
import android.text.Spanned

open class RialtoBaseDelegate(private val registry: RialtoRegistry) : RialtoDelegate, RialtoRegistry by registry {

    override fun registerSpanFactory(key: String, value: String, creator: () -> Any) {
        registry.registerSpanFactory(key, value, creator)
    }

    override fun processAnnotations(text: CharSequence?): CharSequence? {
        return if (text is Spanned) {
            processAnnotations(text, SpannableStringBuilder(text))
        } else {
            text
        }
    }

    internal fun processAnnotations(
            spannedString: Spanned,
            spannableStringBuilder: SpannableStringBuilder
    ): CharSequence {
        spannedString.getSpans(0, spannedString.length, Annotation::class.java)
                .forEach { annotation ->
                    annotation.applySpan(
                            spannedString.getSpanStart(annotation),
                            spannedString.getSpanEnd(annotation),
                            spannableStringBuilder
                    )
                }
        return spannableStringBuilder
    }

    private fun Annotation.applySpan(start: Int, end: Int, spannableStringBuilder: SpannableStringBuilder) =
            registry[key, value].forEach { factory ->
                spannableStringBuilder.setSpan(factory.invoke(), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
            }
}
