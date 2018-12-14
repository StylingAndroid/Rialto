package com.stylingandroid.rialto.app;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import com.stylingandroid.rialto.format.SpannableFormatterKt;

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
        registerSpanFactory("format", "bold_underline", () -> new StyleSpan(Typeface.BOLD));
        registerSpanFactory("format", "bold_underline", UnderlineSpan::new);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.format_string);
        textView.setText(SpannableFormatterKt.getFormattedText(getResources(), R.string.formatted_italic, "formatted"));
    }
}
