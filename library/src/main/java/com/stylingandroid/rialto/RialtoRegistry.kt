package com.stylingandroid.rialto

interface RialtoRegistry {

    operator fun get(key: String, value: String): FactorySet

    fun registerSpanFactory(key: String, value: String, creator: () -> Any)

    fun copy(): RialtoRegistry
}
