package com.stylingandroid.rialto.app

import android.content.Context
import android.graphics.Typeface
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.v4.content.res.ResourcesCompat
import android.text.Spanned
import android.text.style.TypefaceSpan
import android.view.View
import android.widget.TextView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import com.stylingandroid.rialto.support.widget.RialtoTextView
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

    override fun matchesSafely(item: TextView?): Boolean {
        (item?.text as? Spanned)?.also { spanned ->
            spanned.getSpans(0, spanned.length, type.classType).forEach { span ->
                //println("Span: $type ${spanned.getSpanStart(span)}..${spanned.getSpanEnd(span)}")
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

class TypefaceSpanMatcher(private val context: Context, private val typefaceName: String) :
    SpanTypeMatcher<TypefaceSpan>(TypefaceSpan::class.java) {
    override fun describeTo(description: Description?) {
        description?.appendText("typeface name:")?.appendValue(typefaceName)
    }

    override fun matches(item: Any?): Boolean {
        val expected: Typeface? = context.resources.getIdentifier("font", typefaceName, context.packageName).let { id ->
            ResourcesCompat.getFont(context, id)
        }
        return (item as? TypefaceSpan)?.typeface?.equals(expected) ?: false
    }
}
