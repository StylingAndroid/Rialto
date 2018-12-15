package com.stylingandroid.rialto

import android.support.v4.view.LayoutInflaterCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.ViewFactory

class RialtoDelegateImpl(activity: AppCompatActivity) : RialtoBaseDelegate(
    (activity.application as? RialtoRegistry) ?: Registry()
) {

    init {
        LayoutInflaterCompat.setFactory2(activity.layoutInflater, ViewFactory { context ->
            RialtoFactoryContext(context, this)
        })
    }
}
