package com.stylingandroid.rialto.fonts

import android.content.Context
import android.content.res.Resources
import androidx.annotation.ArrayRes
import androidx.core.content.res.ResourcesCompat
import com.stylingandroid.rialto.RialtoDelegate

class FontRegistrar @JvmOverloads constructor(
    private val context: Context,
    private val resources: Resources = context.resources
) {

    fun registerFonts(delegate: RialtoDelegate, key: String, @ArrayRes preloadedFonts: Int) {
        resources.getStringArray(preloadedFonts)
            .map { it.removePrefix("res/font/").removeSuffix(".xml") }
            .forEach { fontName ->
                val typeface = ResourcesCompat.getFont(
                    context, resources.getIdentifier(
                        fontName,
                        "font",
                        context.packageName
                    )
                )
                delegate.registerSpanFactory(key, fontName) {
                    CustomTypefaceSpan(typeface)
                }
            }
    }
}
