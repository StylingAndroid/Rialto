package com.stylingandroid.rialto.format

import android.content.res.Resources
import android.text.SpannableStringBuilder
import android.text.SpannedString
import java.util.regex.Pattern

fun Resources.getFormattedText(id: Int, vararg formatArgs: Any): CharSequence {
    val text = getText(id)
    return if (text is SpannedString) {
        @Suppress("SpreadOperator") // really need to use the spread operator here for Java interoperability
        SpannableFormatter().format(text, *formatArgs)
    } else text
}

class SpannableFormatter {
    private val index = "(\\d+\\\$)"
    private val width = "(\\d+)?"
    private val precision = "(\\.\\d+)?"
    private val general = "(-)?($width[bBhHsScC])"
    private val character = "(-)?($width[cC])"
    private val decimal = "(([-+ 0,(]*)?${width}d)"
    private val integral = "(([-#+ 0(]*)?$width[oxX])"
    private val floatingPoint = "(([-#+ 0(]*)?$width$precision[eEfgG])"
    private val floatingPointHex = "(([-#+ ]*)?$width$precision[aA])"
    private val dateTime = "(([-]*)?$width[tT][HIklMSLNpzZsQBbhAaCYyjmdeRTrDFc])"
    private val percent = "(-?$width%)"
    private val lineSeparator = "(n)"
    internal val pattern = Pattern.compile(
        @Suppress("MaxLineLength")
        "%$index($general|$character|$decimal|$integral|$floatingPoint|$floatingPointHex|$dateTime|$percent|$lineSeparator)"
    )

    fun format(spanned: SpannedString, vararg formatArgs: Any?): CharSequence {
        val builder = SpannableStringBuilder(spanned)
        pattern.matcher(builder).apply {
            while (find()) {
                @Suppress("SpreadOperator") // really need to use the spread operator here for Java interoperability
                String.format(builder.substring(start() until end()), *formatArgs)
                    .also { replacement ->
                        builder.replace(start(), end(), replacement)
                    }
                reset()
            }
        }
        return builder
    }
}
