package com.stylingandroid.rialto

/**
 * RialtoDelegate is the public facing interface of Rialto and has different implementations for
 * different support library configurations.
 */
interface RialtoDelegate {
    /**
     * Registers a span factory with a given annotation key / value pair
     *
     * @property key The annotation key to register the creator with
     * @property value The annotation value to register the creator with
     * @property creator A lambda which will be invoked to create new instances of the required span
     */
    fun registerSpanFactory(key: String, value: String, creator: () -> Any)

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
