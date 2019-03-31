package com.tjxing.parser.result;

import com.tjxing.parser.StringStream;

import java.util.Optional;
import java.util.function.Supplier;

public interface Result<T> {

    boolean isOK();

    Optional<T> get();

    default T getOrElse(T t) {
        return get().orElse(t);
    }

    default T getOrElse(Supplier<T> f) {
        return get().orElseGet(f);
    }

    default T getOrNull() {
        return getOrElse((T) null);
    }

    Optional<String> getError();

    default String getErrorOrNull() {
        return getError().orElse(null);
    }

    StringStream getNext();

}
