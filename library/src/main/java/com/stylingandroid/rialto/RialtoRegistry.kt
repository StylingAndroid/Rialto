package com.stylingandroid.rialto

interface RialtoRegistry {

    operator fun get(key: String, value: String): FactorySet

    /**
     * Registers a span factory with a given annotation key / value pair
     *
     * @property key The annotation key to register the creator with
     * @property value The annotation value to register the creator with
     * @property creator A lambda which will be invoked to create new instances of the required span
     */
    fun registerSpanFactory(key: String, value: String, creator: () -> Any)

    fun copy(): RialtoRegistry
}
