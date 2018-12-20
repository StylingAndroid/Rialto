package com.stylingandroid.rialto.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import androidx.annotation.FontRes;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

@Implements(ResourcesCompat.class)
public class ShadowResourcesCompat {

    @Implementation
    public static Typeface getFont(@NonNull Context context, @FontRes int id) throws Resources.NotFoundException {
        return Typeface.DEFAULT;
    }
}
