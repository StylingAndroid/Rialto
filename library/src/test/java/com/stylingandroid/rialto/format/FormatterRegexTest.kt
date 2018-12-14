package com.stylingandroid.rialto.format

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.util.regex.Matcher
import kotlin.test.assertEquals

@RunWith(Parameterized::class)
class FormatterRegexTest(
    private val string: String,
    @Suppress("UNUSED_PARAMETER") description: String,
    private val expectedMatches: Int
) {

    private val formatter = SpannableFormatter()

    companion object {

        @JvmStatic
        @Parameterized.Parameters(name = "{1}: \"{0}\"")
        fun params() = listOf(
            arrayOf("Test", "No format", 0),
            arrayOf("%1\$s", "Format only", 1),
            arrayOf("%1\$s Text", "Format start", 1),
            arrayOf("Text %1\$s", "Format end", 1),
            arrayOf("%1\$s %2\$d", "Double with space", 2),
            arrayOf("%1\$s%2\$d", "Double no space", 2),
            arrayOf("%1\$s%1\$d", "Duplicate no space", 2),
            arrayOf("%1\$b %1\$-b %1\$B %1\$-B", "Boolean", 4),
            arrayOf("%1\$h %1\$-h %1\$H %1\$-H", "Hex", 4),
            arrayOf("%1\$s %1\$-s %1\$S %1\$-S %1\$8s %1\$-15s %1\$7S %1\$-1S", "String", 8),
            arrayOf("%1\$c %1\$-c %1\$C %1\$-C %1\$8c %1\$-15c %1\$7C %1\$-1C", "Char", 8),
            arrayOf("%1\$d %1\$-d %1\$8d %1\$-15d %1\$,7d %1\$-,1d %1\$(-1d", "Decimal", 7),
            arrayOf("%1\$o %1\$-o %1\$8o %1\$-15o %1\$+ 7o %1\$-+01o %1\$(-1o", "Octal", 7),
            arrayOf("%1\$x %1\$-X %1\$8x %1\$-15X %1\$+ 7x %1\$-+01X %1\$(-1x", "Hex", 7),
            arrayOf("%1\$e %1\$-E %1\$8e %1\$-15.4E %1\$+ 7.2e %1\$-+01E %1\$(-1e", "Scientific", 7),
            arrayOf("%1\$f %1\$-f %1\$8f %1\$-15.4f %1\$+ 7.2f %1\$-+01f %1\$(-1f %1\$(-1F", "Float", 7),
            arrayOf("%1\$g %1\$-G %1\$8g %1\$-15.4G %1\$+ 7.2g %1\$-+01G %1\$(-1g", "Scientific rounded", 7),
            arrayOf("%1\$a %1\$-A %1\$8a %1\$-15.4A %1\$+ 7.2a %1\$-+01A %1\$-1a", "Hex Float", 7),
            arrayOf("%1\$-tH %1\$TI %1\$-tk %1\$8tl %1\$-15tM %1\$7TS %1\$-1tL %1\$-1tN %1\$-1tp %1\$-1tz %1\$-1tZ %1\$-1ts %1\$-1tQ", "Time", 13),
            arrayOf("%1\$-tB %1\$Tb %1\$-th %1\$8tA %1\$-15ta %1\$7TC %1\$-1tY %1\$-1ty %1\$-1tj %1\$-1tm %1\$-1td %1\$-1te", "Date", 12),
            arrayOf("%1\$-tR %1\$TT %1\$-tr %1\$8tD %1\$-15tF %1\$7Tc", "Date/Time", 6),
            arrayOf("%1\$-t %1\$T", "Date / Time without qualifiers", 0),
            arrayOf("%1\$% %1\$-% %1\$-4%", "Percent", 3),
            arrayOf("%1\$n %1\$-n %1\$2n", "Line Separator", 1)
        )
    }

    @Test
    fun test() {
        val matches = formatter.pattern.matcher(string).findCount()
        assertEquals(expectedMatches, matches)
    }

    private fun Matcher.findCount(): Int {
        var count = 0
        while (find()) count++
        return count
    }
}
