package com.stylingandroid.rialto.java;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;

import com.stylingandroid.rialto.app.RialtoActivity;

import kotlin.jvm.functions.Function0;

public class MainActivity extends RialtoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerSpanFactory("format", "bold", new Function0<CharacterStyle>() {
            @Override
            public CharacterStyle invoke() {
                return new StyleSpan(Typeface.BOLD);
            }
        });
        registerSpanFactory("format", "italic", () -> new StyleSpan(Typeface.ITALIC) );
        setContentView(R.layout.activity_main);
    }
}
