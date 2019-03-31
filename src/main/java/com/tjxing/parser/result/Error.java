package com.tjxing.parser.result;

import com.tjxing.parser.StringStream;

import java.util.Optional;

public class Error<T> implements Result<T> {

    final private String message;
    final private StringStream next;

    public Error(String message, StringStream next) {
        this.message = message;
        this.next = next;
    }

    @Override
    public boolean isOK() {
        return false;
    }

    @Override
    public Optional<T> get() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getError() {
        return Optional.ofNullable(message);
    }

    @Override
    public StringStream getNext() {
        return next;
    }

}
