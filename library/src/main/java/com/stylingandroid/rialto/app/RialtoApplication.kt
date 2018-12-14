package com.stylingandroid.rialto.app

import android.app.Application
import com.stylingandroid.rialto.Registry
import com.stylingandroid.rialto.RialtoRegistry

class RialtoApplication @JvmOverloads constructor(
    registry: RialtoRegistry = Registry()
) : Application(), RialtoRegistry by registry
