package com.stylingandroid.rialto.font

import android.content.Context
import android.content.res.Resources
import android.support.annotation.ArrayRes
import android.support.v4.content.res.ResourcesCompat
import com.stylingandroid.rialto.RialtoRegistry

class FontRegistrar @JvmOverloads constructor(
    private val context: Context,
    private val resources: Resources = context.resources
) {

    fun registerFonts(registry: RialtoRegistry, key: String, @ArrayRes preloadedFonts: Int) {
        resources.getStringArray(preloadedFonts)
            .map { it.removePrefix("res/font/").removeSuffix(".xml") }
            .forEach { fontName ->
                try {
                    ResourcesCompat.getFont(
                        context,
                        resources.getIdentifier(fontName, "font", context.packageName)
                    )
                } catch (e: Resources.NotFoundException) {
                    null
                }?.also { typeface ->
                    registry.registerSpanFactory(key, fontName) {
                        CustomTypefaceSpan(typeface)
                    }
                }
            }
    }
}
