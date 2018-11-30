package com.stylingandroid.rialto.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.stylingandroid.rialto.RialtoDelegate
import com.stylingandroid.rialto.RialtoDelegateImpl
import com.stylingandroid.rialto.RialtoRegistry

open class RialtoActivity : AppCompatActivity(), RialtoDelegate, LifecycleOwner {

    private lateinit var delegate: RialtoDelegate

    override fun registerSpanFactory(key: String, value: String, creator: () -> Any) {
        delegate.registerSpanFactory(key, value, creator)
    }

    override fun processAnnotations(text: CharSequence?): CharSequence? =
            delegate.processAnnotations(text)

    override fun onCreate(savedInstanceState: Bundle?) {
        delegate = RialtoDelegateImpl(this, application as? RialtoRegistry)
        super.onCreate(savedInstanceState)
    }
}

