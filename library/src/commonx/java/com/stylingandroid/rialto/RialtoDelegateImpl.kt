package com.stylingandroid.rialto

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ViewFactory
import androidx.core.view.LayoutInflaterCompat
import androidx.lifecycle.LifecycleOwner

class RialtoDelegateImpl(activity: AppCompatActivity) : RialtoBaseDelegate() {

    init {
        LayoutInflaterCompat.setFactory2(activity.layoutInflater, ViewFactory(activity as LifecycleOwner) { context ->
            RialtoFactoryContext(context, this)
        })
    }
}
