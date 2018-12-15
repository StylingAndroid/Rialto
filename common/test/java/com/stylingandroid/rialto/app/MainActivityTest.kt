package com.stylingandroid.rialto.app

import android.graphics.Typeface
import android.text.style.UnderlineSpan
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.stylingandroid.rialto.format.getFormattedText
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun `Given an inflated layout When we use default values Then a bold span surrounds the bold text of formatted_text_view`() {
        onView(withId(R.id.formatted_text_view)).check(
            matches(withSpan(withStyle(Typeface.BOLD), 33..36))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then an italic span surrounds the italic text of formatted_text_view`() {
        onView(withId(R.id.formatted_text_view)).check(
            matches(withSpan(withStyle(Typeface.ITALIC), 72..77))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then an underline span surrounds the underline text of formatted_text_view`() {
        onView(withId(R.id.formatted_text_view)).check(
            matches(withSpan(withType(UnderlineSpan::class.java), 39..48))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then a bold span surrounds the bold underline text of formatted_text_view`() {
        onView(withId(R.id.formatted_text_view)).check(
            matches(withSpan(withStyle(Typeface.BOLD), 51..65))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then an underline span surrounds the bold underline text of formatted_text_view`() {
        onView(withId(R.id.formatted_text_view)).check(
            matches(withSpan(withType(UnderlineSpan::class.java), 51..65))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then a pacifico typeface span surrounds the pacifico text of formatted_text_view`() {
        onView(withId(R.id.formatted_text_view)).check(
            matches(withSpan(withTypeface(), 89..91))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then a audiowide typeface span surrounds the audiowide text of formatted_text_view`() {
        onView(withId(R.id.formatted_text_view)).check(
            matches(withSpan(withTypeface(), 93..101))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then a bold span surrounds the bold text of formatted_edit_text`() {
        onView(withId(R.id.formatted_edit_text)).check(
            matches(withSpan(withStyle(Typeface.BOLD), 33..36))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then an italic span surrounds the italic text of formatted_edit_text`() {
        onView(withId(R.id.formatted_edit_text)).check(
            matches(withSpan(withStyle(Typeface.ITALIC), 72..77))
        )
    }


    @Test
    fun `Given an inflated layout When we use default values Then an underline span surrounds the underline text of formatted_edit_text`() {
        onView(withId(R.id.formatted_edit_text)).check(
            matches(withSpan(withType(UnderlineSpan::class.java), 39..48))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then a bold span surrounds the bold underline text of formatted_edit_text`() {
        onView(withId(R.id.formatted_edit_text)).check(
            matches(withSpan(withStyle(Typeface.BOLD), 51..65))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then a pacifico typeface span surrounds the pacifico text of formatted_edit_text`() {
        onView(withId(R.id.formatted_edit_text)).check(
            matches(withSpan(withTypeface(), 89..91))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then a audiowide typeface span surrounds the audiowide text of formatted_edit_text`() {
        onView(withId(R.id.formatted_edit_text)).check(
            matches(withSpan(withTypeface(), 93..101))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then an underline span surrounds the bold underline text of formatted_edit_text`() {
        onView(withId(R.id.formatted_edit_text)).check(
            matches(withSpan(withType(UnderlineSpan::class.java), 51..65))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then a bold span does not get added to the text of unformatted_text_view`() {
        onView(withId(R.id.unformatted_text_view)).check(
            matches(not(withSpan(withStyle(Typeface.BOLD))))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then a italic span does not get added to the text of unformatted_text_view`() {
        onView(withId(R.id.unformatted_text_view)).check(
            matches(not(withSpan(withStyle(Typeface.ITALIC))))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then a underline span does not get added to the text of unformatted_text_view`() {
        onView(withId(R.id.unformatted_text_view)).check(
            matches(not(withSpan(withType(UnderlineSpan::class.java))))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then a pacifico typeface span surrounds the pacifico text of unformatted_text_view`() {
        onView(withId(R.id.unformatted_text_view)).check(
            matches(not(withSpan(withTypeface(), 89..91)))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then a audiowide typeface span surrounds the audiowide text of unformatted_text_view`() {
        onView(withId(R.id.unformatted_text_view)).check(
            matches(not(withSpan(withTypeface(), 93..101)))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then a bold span does not get added to the text of unformatted_edit_text`() {
        onView(withId(R.id.unformatted_edit_text)).check(
            matches(not(withSpan(withStyle(Typeface.BOLD))))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then a italic span does not get added to the text of unformatted_edit_text`() {
        onView(withId(R.id.unformatted_edit_text)).check(
            matches(not(withSpan(withStyle(Typeface.ITALIC))))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then a underline span does not get added to the text of unformatted_edit_text`() {
        onView(withId(R.id.unformatted_edit_text)).check(
            matches(not(withSpan(withType(UnderlineSpan::class.java))))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then a pacifico typeface span surrounds the pacifico text of unformatted_edit_text`() {
        onView(withId(R.id.unformatted_edit_text)).check(
            matches(not(withSpan(withTypeface(), 89..91)))
        )
    }

    @Test
    fun `Given an inflated layout When we use default values Then a audiowide typeface span surrounds the audiowide text of unformatted_edit_text`() {
        onView(withId(R.id.unformatted_edit_text)).check(
            matches(not(withSpan(withTypeface(), 93..101)))
        )
    }

    @Test
    fun `Given an inflated layout When we set text on formatted_text_view using a formatted string Then an italic span is added`() {
        onView(withId(R.id.format_string))
                .check(matches(withSpan(withStyle(Typeface.ITALIC), 10..18)))
    }

    @Test
    fun `Given an inflated layout When we set text on formatted_text_view using a different formatted string Then a bold span is added`() {
        val newText = rule.activity.resources.getFormattedText(R.string.formatted_bold, "formatted")
        onView(withId(R.id.format_string))
                .perform(setText(newText))
                .check(matches(withSpan(withStyle(Typeface.BOLD), 10..18)))
    }

    @Test
    fun `Given an inflated layout When we set text on unformatted_text_view using a formatted string Then a bold span is not added`() {
        val newText = rule.activity.resources.getFormattedText(R.string.formatted_bold, "formatted")
        onView(withId(R.id.unformatted_text_view))
            .perform(setText(newText))
            .check(matches(not(withSpan(withStyle(Typeface.BOLD)))))
    }
}
