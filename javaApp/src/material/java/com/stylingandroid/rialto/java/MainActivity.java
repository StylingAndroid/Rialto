package com.stylingandroid.rialto.java;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.stylingandroid.rialto.RialtoDelegate;
import com.stylingandroid.rialto.RialtoDelegateImpl;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        delegate = new RialtoDelegateImpl(this);
        registerSpanFactory("format", "bold", new Function0<CharacterStyle>() {
            @Override
            public CharacterStyle invoke() {
                return new StyleSpan(Typeface.BOLD);
            }
        });
        registerSpanFactory("format", "italic", () -> new StyleSpan(Typeface.ITALIC));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
