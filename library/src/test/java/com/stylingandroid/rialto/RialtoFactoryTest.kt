package com.stylingandroid.rialto

import android.text.Annotation
import android.text.SpannableStringBuilder
import android.text.SpannedString
import android.text.style.CharacterStyle
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyInt
import kotlin.test.assertSame

class RialtoFactoryTest {

    private val characterStyle1: CharacterStyle = mock()
    private val spannedString: SpannedString = mock()
    private val annotation: Annotation = mock()
    private val spannableStringBuilder: SpannableStringBuilder = mock()

    @Test
    fun `Given an initialised factory When we match an annotation span Then setSpan is called on the builder`() {
        val delegate = RialtoBaseDelegate()
        delegate.registerSpanFactory("key", "value") { characterStyle1 }
        whenever(spannedString.getSpans(anyInt(), anyInt(), any(Class::class.java))).thenReturn(arrayOf(annotation))
        whenever(spannedString.getSpanStart(annotation)).thenReturn(4)
        whenever(spannedString.getSpanEnd(annotation)).thenReturn(6)
        whenever(annotation.key).thenReturn("key")
        whenever(annotation.value).thenReturn("value")

        delegate.processAnnotations(spannedString, spannableStringBuilder)

        verify(spannableStringBuilder).setSpan(characterStyle1, 4, 6, SpannedString.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

    @Test
    fun `Given a String input When we call processAnnotations() Then the same object is returned`() {
        val delegate = RialtoBaseDelegate()
        delegate.registerSpanFactory("key", "value") { characterStyle1 }
        val string = "Hello World!"

        val result = delegate.processAnnotations(string)

        assertSame(string, result)
    }
}
