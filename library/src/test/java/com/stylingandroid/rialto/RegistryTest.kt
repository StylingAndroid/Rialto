package com.stylingandroid.rialto

import android.text.style.CharacterStyle
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame
import kotlin.test.assertTrue

class RegistryTest {

    private lateinit var registryMap: MutableMap<String, MutableMap<String, MutableSet<Factory>>>
    private lateinit var registry: Registry
    private val characterStyle1: CharacterStyle = mock()
    private val characterStyle2: CharacterStyle = mock()

    @Test
    fun `Given an empty registry When we don't register anything Then the backing collection is empty`() {
        createEmptyRegistry()

        assertTrue { registryMap.isEmpty() }
    }

    @Test
    fun `Given an empty registry When we register a single key value pair Then the backing collection has a single key item`() {
        createEmptyRegistry()

        registry.registerSpanFactory("key", "value") { characterStyle1 }

        assertEquals(1, registryMap.size)
    }

    @Test
    fun `Given an empty registry When we register a single key value pair Then the backing collection has a single value item`() {
        createEmptyRegistry()

        registry.registerSpanFactory("key", "value") { characterStyle1 }

        assertEquals(1, registry["key", "value"].size)
    }

    @Test
    fun `Given an empty registry When we register a single key value pair Then the backing collection has a single factory item`() {
        createEmptyRegistry()

        registry.registerSpanFactory("key", "value") { characterStyle1 }

        assertEquals(1, registry["key", "value"].size)
    }

    @Test
    fun `Given an empty registry When we register a the same key value pair multiple times Then the backing collection has a single key item`() {
        createEmptyRegistry()

        registry.registerSpanFactory("key", "value") { characterStyle1 }
        registry.registerSpanFactory("key", "value") { characterStyle2 }

        assertEquals(1, registryMap.size)
    }

    @Test
    fun `Given an empty registry When we register a the same key value pair multiple times Then the backing collection has a two value items`() {
        createEmptyRegistry()

        registry.registerSpanFactory("key", "value") { characterStyle1 }
        registry.registerSpanFactory("key", "value") { characterStyle2 }

        assertEquals(2, registry["key", "value"].size)
    }

    @Test
    fun `Given an empty registry When we register a the two key values Then the backing collection has a two value items`() {
        createEmptyRegistry()

        registry.registerSpanFactory("key", "value1") { characterStyle1 }
        registry.registerSpanFactory("key", "value2") { characterStyle1 }

        assertEquals(2, registryMap["key"]?.size)
    }

    @Test
    fun `Given an empty registry When we register a the two key values Then the backing collection has separate factory items`() {
        createEmptyRegistry()

        registry.registerSpanFactory("key", "value1") { characterStyle1 }
        registry.registerSpanFactory("key", "value2") { characterStyle1 }

        assertEquals(1, registry["key", "value1"].size)
        assertEquals(1, registry["key", "value2"].size)
    }

    @Test
    fun `Given an initialised parent registry When we clone it Then the backing collection has identical factory items`() {
        createEmptyRegistry()
        val factory: () -> Any = { characterStyle1 }
        registry.registerSpanFactory("key", "value1", factory)

        val child = Registry(registry)

        assertEquals(1, child["key", "value1"].size)
        assertSame(factory, registry["key", "value1"].first())
    }

    private fun createEmptyRegistry() {
        registryMap = mutableMapOf()
        registry = Registry(registryMap)
    }
}
