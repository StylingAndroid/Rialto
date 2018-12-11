package com.stylingandroid.rialto.app

import android.text.style.StyleSpan
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

fun withSpan(range: ClosedRange<Int>, type: SpanTypeMatcher<*>) =
    SpanMatcher(range, type)

fun withStyle(style: Int) = StyleSpanMatcher(style)

class StyleSpanMatcher(private val style: Int) : SpanTypeMatcher<StyleSpan>(StyleSpan::class.java) {

    override fun describeTo(description: Description?) {
        description?.appendText("with style:")?.appendValue(style)
    }

    override fun matches(item: Any?): Boolean =
        ((item as? StyleSpan)?.style == style)
}

fun withType(classType: Class<*>) = SpanTypeMatcher<Any>(classType)

open class SpanTypeMatcher<T>(val classType: Class<*>) : BaseMatcher<T>() {

    override fun describeTo(description: Description?) {
        description?.appendText(" with type: ")?.appendValue(classType.simpleName)
    }

    override fun matches(item: Any?): Boolean = classType.isInstance(item)
}
