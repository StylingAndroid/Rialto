package com.stylingandroid.rialto

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ViewFactory
import androidx.core.view.LayoutInflaterCompat

class RialtoDelegateImpl(activity: AppCompatActivity) : RialtoBaseDelegate(
    (activity.application as? RialtoRegistry) ?: Registry()
) {

    init {
        LayoutInflaterCompat.setFactory2(activity.layoutInflater, ViewFactory { context ->
            RialtoFactoryContext(context, this)
        })
    }
}
