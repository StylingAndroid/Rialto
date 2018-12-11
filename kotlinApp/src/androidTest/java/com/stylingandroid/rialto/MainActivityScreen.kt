package com.stylingandroid.rialto

//class MainActivityScreen : Screen<MainActivityScreen>() {
//    val formattedTextView = KRialtoTextView { withId(R.id.formatted_text_view) }
//    val formattedEditText = KEditText { withId(R.id.formatted_edit_text) }
//    val unformattedTextView = KTextView { withId(R.id.unformatted_text_view) }
//    val unformattedEditText = KEditText { withId(R.id.unformatted_edit_text) }
//}
//
//class KRialtoTextView : KBaseView<KRialtoTextView>, TextViewAssertions {
//    constructor(function: ViewBuilder.() -> Unit) : super(function)
//    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
//    constructor(parent: androidx.test.espresso.DataInteraction, function: ViewBuilder.() -> Unit) : super(parent, function)
//
//    fun containsSpanAt(start: Int, end: Int, matcher: Matcher<*>) {
//        view.check(androidx.test.espresso.assertion.ViewAssertions.matches(TextViewMatcher(SpanMatcher(start, end, matcher))))
//    }
//
//    fun containsStyleAt(start: Int, end: Int, style: Int) {
//        containsSpanAt(start, end, styleMatcher(style))
//    }
//}
//
//class SpanMatcher(val start: Int, val end: Int, private val matcher: Matcher<*>) : BaseMatcher<Any>() {
//    override fun describeTo(description: Description?) {
//        description?.appendText("Span Matcher: ")
//        matcher.describeTo(description)
//    }
//
//    override fun matches(item: Any?): Boolean = matcher.matches(item)
//}
//
//class TextViewMatcher(private val matcher: SpanMatcher) : androidx.test.espresso.matcher.BoundedMatcher<View, TextView>(TextView::class.java) {
//    override fun describeTo(description: Description?) {
//        description?.appendText("TextViewMatcher: ")
//        matcher.describeTo(description)
//    }
//
//    override fun describeMismatch(item: Any?, description: Description?) {
//        when (item) {
//            is TextView -> {
////            ((item.text as? SpannedString)?.run {
////                getSpans(0, length, Any::class.java).forEach { span ->
////                    if (span)
////                }
////            }
//            }
//            null -> description?.appendText("Expected a TextView but got <null>")
//            else -> description?.appendText("Expected a TextView but got a ${item.javaClass.simpleName}")
//        }
//    }
//
//    override fun matchesSafely(item: TextView?): Boolean {
//        (item?.text as? SpannedString)?.run {
//            getSpans(0, length, Any::class.java).forEach { span ->
//                if (getSpanStart(span) == matcher.start && getSpanEnd(span) == matcher.end && matcher.matches(span)) {
//                    return true
//                }
//            }
//        }
//        return false
//    }
//}
//
//fun styleMatcher(requiredStyle: Int): Matcher<StyleSpan> = object : BaseMatcher<StyleSpan>() {
//    override fun describeTo(description: Description?) {
//        description?.appendText("StyleMatcher!")
//    }
//
//    override fun matches(item: Any?): Boolean {
//        return (item as? StyleSpan)?.run {
//            style == requiredStyle
//        } ?: false
//    }
//
//}
