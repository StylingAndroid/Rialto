package com.stylingandroid.rialto.app;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
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
    }
}
