package com.stylingandroid.rialto

typealias Factory = () -> Any
typealias FactorySet = Set<Factory>

class Registry constructor(
    private val collection: MutableMap<String, MutableMap<String, MutableSet<Factory>>> = mutableMapOf()
) {

    constructor(
        parent: Registry,
        collection: MutableMap<String, MutableMap<String, MutableSet<Factory>>> = mutableMapOf()
    ) : this(collection) {
        collection.putAll(parent.collection)
    }

    private val emptyValueMap = emptyMap<String, FactorySet>()
    private val emptyFactorySet = emptySet<Factory>()

    operator fun get(key: String, value: String) : FactorySet =
        (collection[key] ?: emptyValueMap)[value] ?: emptyFactorySet


    fun registerFactory(key: String, value: String, factory: () -> Any) {
        (collection[key] ?: let {
            mutableMapOf<String, MutableSet<Factory>>().also { values ->
                collection[key] = values
            }
        }).registerFactory(value, factory)
    }

    private fun MutableMap<String, MutableSet<Factory>>.registerFactory(value: String, factory: () -> Any) {
        (this[value] ?: let {
            mutableSetOf<() -> Any>().also { factories ->
                this[value] = factories
            }
        }).registerFactory(factory)
    }

    private fun MutableSet<Factory>.registerFactory(factory: () -> Any) {
        add(factory)
    }
}
