package com.stylingandroid.rialto.app;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import com.stylingandroid.rialto.RialtoDelegate;
import com.stylingandroid.rialto.RialtoDelegateImpl;
import com.stylingandroid.rialto.RialtoRegistry;
import com.stylingandroid.rialto.format.SpannableFormatterKt;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import kotlin.jvm.functions.Function0;

import java.util.Set;

public class MainActivity extends AppCompatActivity implements RialtoDelegate {

    private RialtoDelegate delegate = null;

    @Override
    public void registerSpanFactory(@NotNull String key, @NotNull String value, @NotNull Function0<?> creator) {
        delegate.registerSpanFactory(key, value, creator);
    }

    @Override
    public CharSequence processAnnotations(CharSequence text) {
        return delegate.processAnnotations(text);
    }

    @NotNull
    @Override
    public Set<Function0<Object>> get(@NotNull String key, @NotNull String value) {
        return delegate.get(key, value);
    }

    @NotNull
    @Override
    public RialtoRegistry copy() {
        return delegate.copy();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        delegate = new RialtoDelegateImpl(this);

        super.onCreate(savedInstanceState);

        registerSpanFactory("format", "bold", new Function0<CharacterStyle>() {
            @Override
            public CharacterStyle invoke() {
                return new StyleSpan(Typeface.BOLD);
            }
        });
        registerSpanFactory("format", "italic", () -> new StyleSpan(Typeface.ITALIC));
        registerSpanFactory("format", "bold_underline", () -> new StyleSpan(Typeface.BOLD));
        registerSpanFactory("format", "bold_underline", UnderlineSpan::new);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.format_string);
        textView.setText(SpannableFormatterKt.getFormattedText(getResources(), R.string.formatted_italic, "formatted"));
    }

}
