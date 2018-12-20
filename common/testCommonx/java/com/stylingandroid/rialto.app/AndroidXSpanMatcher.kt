package com.stylingandroid.rialto.app

import android.content.Context
import android.graphics.Typeface
import android.text.Spanned
import android.text.style.TypefaceSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import com.stylingandroid.rialto.androidx.widget.RialtoTextView
import org.hamcrest.BaseMatcher
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
            println("$spanned: ${type.classType.simpleName}")
            spanned.getSpans(0, spanned.length, type.classType).forEach { span ->
                println("$spanned: ${spanned.getSpanStart(span)}..${spanned.getSpanEnd(span)} ${span.javaClass.simpleName}")
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

class TypefaceSpanMatcher(private val context: Context, private val typefaceName: String) : BaseMatcher<TypefaceSpan>() {
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
