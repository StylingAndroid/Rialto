package com.stylingandroid.rialto.format

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.SpannedString
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(RobolectricTestRunner::class)
class SpannableFormatterTest {

    private val string1 = "String1"
    private val string2 = "String2"

    private val sourceString = "Test %1\$s and %2\$s."

    private lateinit var spanned: SpannedString
    private val expected = "Test $string1 and $string2."

    private val formatter = SpannableFormatter()

    @Before
    fun setup() {
        val source =  SpannableStringBuilder(sourceString)
        source.setSpan(StyleSpan(Typeface.BOLD), 5, 8, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        source.setSpan(UnderlineSpan(), 10, 12, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        spanned = SpannedString(source)
    }

    @Test
    fun `Given a string with format placeholders When we format it Then the correct result is produced`() {
        val actual = formatter.format(spanned, string1, string2)

        assertEquals(expected, actual.toString())
    }

    @Test
    fun `Given a string with a span around a format placeholder When we format it Then the span is still present`() {
        val actual = formatter.format(spanned, string1, string2)

        val span = (actual as? Spanned)?.getSpans(0, actual.length, StyleSpan::class.java)?.first()

        assertNotNull(span)
    }

    @Test
    fun `Given a string a span around a format placeholder When we format it Then the span is at the correct start location`() {
        val actual = formatter.format(spanned, string1, string2)

        val span = (actual as? Spanned)?.getSpans(0, actual.length, StyleSpan::class.java)?.first()

        assertEquals(5, (actual as? Spanned)?.getSpanStart(span) ?: 0)
    }

    @Test
    fun `Given a string with a span around a format placeholder When we format it Then the span is in the correct location`() {
        val actual = formatter.format(spanned, string1, string2)

        val span = (actual as? Spanned)?.getSpans(0, actual.length, StyleSpan::class.java)?.first()

        assertEquals(12, (actual as? Spanned)?.getSpanEnd(span) ?: 0)
    }

    @Test
    fun `Given a string with a span around plain text When we format it Then the span is still present`() {
        val actual = formatter.format(spanned, string1, string2)

        val span = (actual as? Spanned)?.getSpans(0, actual.length, UnderlineSpan::class.java)?.first()

        assertNotNull(span)
    }

    @Test
    fun `Given a string a span around plain text When we format it Then the span is at the correct start location`() {
        val actual = formatter.format(spanned, string1, string2)

        val span = (actual as? Spanned)?.getSpans(0, actual.length, UnderlineSpan::class.java)?.first()

        assertEquals(13, (actual as? Spanned)?.getSpanStart(span) ?: 0)
    }

    @Test
    fun `Given a string with a span around plain text When we format it Then the span is in the correct location`() {
        val actual = formatter.format(spanned, string1, string2)

        val span = (actual as? Spanned)?.getSpans(0, actual.length, UnderlineSpan::class.java)?.first()

        assertEquals(15, (actual as? Spanned)?.getSpanEnd(span) ?: 0)
    }
}
