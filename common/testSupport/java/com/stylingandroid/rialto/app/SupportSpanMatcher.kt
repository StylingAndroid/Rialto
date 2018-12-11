package com.stylingandroid.rialto.app

import android.support.test.espresso.matcher.BoundedMatcher
import android.text.Spanned
import android.view.View
import android.widget.TextView
import org.hamcrest.Description

class SpanMatcher(
    private val range: ClosedRange<Int>,
    private val type: SpanTypeMatcher<*>
) : BoundedMatcher<View, TextView>(TextView::class.java) {

    override fun describeTo(description: Description?) {
        description?.appendText(" with span of type:")?.appendValue(type)
        description?.appendText(" in range:")?.appendValue(range)
    }

    override fun matchesSafely(item: TextView?): Boolean {
        (item?.text as? Spanned)?.also { spanned ->
            spanned.getSpans(0, spanned.length, type.classType).forEach { span ->
                println("Span: $type ${spanned.getSpanStart(span)}..${spanned.getSpanEnd(span)}")
                if (spanned.getSpanStart(span) == range.start && spanned.getSpanEnd(span) == range.endInclusive + 1) {
                    return type.matches(span)
                }
            }
        }
        return false
    }
}
