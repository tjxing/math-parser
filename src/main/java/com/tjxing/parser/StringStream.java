package com.tjxing.parser;

import java.util.Iterator;

public class StringStream implements Iterable<Character> {

    final private String str;
    final private int start;
    final private int end;

    public StringStream(String str) {
        this.str = str;
        this.start = 0;
        this.end = str.length();
    }

    private StringStream(String str, int start, int end) {
        this.str = str;
        this.start = start;
        this.end = end;
    }

    public boolean hasNext() {
        return start < end;
    }

    public Character head() {
        return str.charAt(start);
    }

    public Iterator<Character> head(int n) {
        return new StringStream(str, start, start + n).iterator();
    }

    public Character charAt(int n) {
        return str.charAt(start + n);
    }

    public StringStream next() {
        return new StringStream(str, start + 1, end);
    }

    public StringStream next(int n) {
        return new StringStream(str, start + n, end);
    }

    @Override
    public Iterator<Character> iterator() {
        return new Iterator<Character>() {

            int offset = start;

            @Override
            public boolean hasNext() {
                return offset < end;
            }

            @Override
            public Character next() {
                return str.charAt(offset++);
            }

        };
    }
}
