package com.stylingandroid.rialto.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.stylingandroid.rialto.FactorySet
import com.stylingandroid.rialto.RialtoDelegate
import com.stylingandroid.rialto.RialtoDelegateImpl
import com.stylingandroid.rialto.RialtoRegistry

open class RialtoActivity : AppCompatActivity(), RialtoDelegate {

    private lateinit var delegate: RialtoDelegate

    override fun registerSpanFactory(key: String, value: String, creator: () -> Any) {
        delegate.registerSpanFactory(key, value, creator)
    }

    override fun processAnnotations(text: CharSequence?): CharSequence? =
        delegate.processAnnotations(text)

    override fun get(key: String, value: String): FactorySet = delegate.get(key, value)

    override fun copy(): RialtoRegistry = delegate.copy()

    override fun onCreate(savedInstanceState: Bundle?) {
        delegate = RialtoDelegateImpl(this)

        super.onCreate(savedInstanceState)
    }
}

