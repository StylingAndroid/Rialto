package com.stylingandroid.rialto

/**
 * RialtoDelegate is the public facing interface of Rialto and has different implementations for
 * different support library configurations.
 */
interface RialtoDelegate : RialtoRegistry {

    /**
     * Processes the given CharSequence adding registered spans wherever there are
     * annotations matching the registered key / value pair in the source CharSequence
     *
     * @property text The input CharSequence
     * @return The input with any matching spans added. This may be the same as input if no
     * matching annotations were found, or if input is not a SpannedString
     */
    fun processAnnotations(text: CharSequence?): CharSequence?
}
