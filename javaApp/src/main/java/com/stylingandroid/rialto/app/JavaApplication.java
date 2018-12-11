package com.stylingandroid.rialto.app;

import android.app.Application;
import android.text.style.UnderlineSpan;
import com.stylingandroid.rialto.Registry;
import com.stylingandroid.rialto.RialtoRegistry;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class JavaApplication extends Application implements RialtoRegistry {

    private RialtoRegistry delegate = new Registry();

    @NotNull
    @Override
    public Set<Function0<Object>> get(@NotNull String key, @NotNull String value) {
        return delegate.get(key, value);
    }

    @Override
    public void registerSpanFactory(@NotNull String key, @NotNull String value, @NotNull Function0<?> creator) {
        delegate.registerSpanFactory(key, value, creator);
    }

    @NotNull
    @Override
    public RialtoRegistry copy() {
        return delegate.copy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerSpanFactory("format", "underline", UnderlineSpan::new);
    }
}
