package com.stylingandroid.rialto

import android.arch.lifecycle.LifecycleOwner
import android.support.v4.view.LayoutInflaterCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.ViewFactory

class RialtoDelegateImpl(activity: AppCompatActivity) : RialtoBaseDelegate(
    (activity.application as? RialtoRegistry) ?: Registry()
) {

    init {
        LayoutInflaterCompat.setFactory2(activity.layoutInflater, ViewFactory(activity as LifecycleOwner) { context ->
            RialtoFactoryContext(context, this)
        })
    }
}
