package com.stylingandroid.rialto.app

import android.graphics.Typeface
import android.text.style.UnderlineSpan
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CascadingTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun `Given an inflated layout When we use default values Then a bold span surrounds the bold text of formatted_text_view`() {
        onView(withId(R.id.formatted_text_view)).check(matches(
            withSpan(
                33..36,
                withStyle(Typeface.BOLD)
            )
        ))
    }

    @Test
    fun `Given an inflated layout When we use default values Then an italic span surrounds the italic text of formatted_text_view`() {
        onView(withId(R.id.formatted_text_view)).check(matches(
            withSpan(
                59..64,
                withStyle(Typeface.ITALIC)
            )
        ))
    }

    @Test
    fun `Given an inflated layout When we use default values Then a bold span surrounds the bold underline text of formatted_text_view`() {
        onView(withId(R.id.formatted_text_view)).check(matches(
            withSpan(
                39..53,
                withStyle(Typeface.BOLD)
            )
        ))
    }

    @Test
    fun `Given an inflated layout When we use default values Then an underline span surrounds the bold underline text of formatted_text_view`() {
        onView(withId(R.id.formatted_text_view)).check(matches(
            withSpan(
                39..53,
                withType(UnderlineSpan::class.java)
            )
        ))
    }

    @Test
    fun `Given an inflated layout When we use default values Then a bold span surrounds the bold text of formatted_edit_text`() {
        onView(withId(R.id.formatted_edit_text)).check(matches(
            withSpan(
                33..36,
                withStyle(Typeface.BOLD)
            )
        ))
    }

    @Test
    fun `Given an inflated layout When we use default values Then an italic span surrounds the italic text of formatted_edit_text`() {
        onView(withId(R.id.formatted_edit_text)).check(matches(
            withSpan(
                59..64,
                withStyle(Typeface.ITALIC)
            )
        ))
    }

    @Test
    fun `Given an inflated layout When we use default values Then a bold span surrounds the bold underline text of formatted_edit_text`() {
        onView(withId(R.id.formatted_edit_text)).check(matches(
            withSpan(
                39..53,
                withStyle(Typeface.BOLD)
            )
        ))
    }

    @Test
    fun `Given an inflated layout When we use default values Then an underline span surrounds the bold underline text of formatted_edit_text`() {
        onView(withId(R.id.formatted_edit_text)).check(matches(
            withSpan(
                39..53,
                withType(UnderlineSpan::class.java)
            )
        ))
    }
}
