package com.stylingandroid.rialto.app

import android.text.Spanned
import android.view.View
import android.widget.TextView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import com.stylingandroid.rialto.androidx.widget.RialtoTextView
import org.hamcrest.Description
import org.hamcrest.Matcher

class SpanMatcher(
    private val type: SpanTypeMatcher<*>,
    private val range: ClosedRange<Int>? = null
) : BoundedMatcher<View, TextView>(TextView::class.java) {

    override fun describeTo(description: Description?) {
        description?.appendText(" with span of type:")?.appendValue(type)
        description?.appendText(" in range:")?.appendValue(range)
    }

    override fun matchesSafely(item: TextView): Boolean {
        (item.text as? Spanned)?.also { spanned ->
            spanned.getSpans(0, spanned.length, type.classType).forEach { span ->
                range?.also {
                    if (spanned.getSpanStart(span) == range.start && spanned.getSpanEnd(span) == range.endInclusive + 1) {
                        return type.matches(span)
                    }
                } ?: return true
            }
        }
        return false
    }
}

class SetTextAction(private val charSequence: CharSequence) : ViewAction {

    override fun getDescription(): String = "replace text"

    override fun getConstraints(): Matcher<View> =
        isAssignableFrom(RialtoTextView::class.java)

    override fun perform(uiController: UiController?, view: View?) {
        (view as? RialtoTextView)?.setText(charSequence, TextView.BufferType.SPANNABLE)
    }
}
