package com.vicefix.crackwatch.utils;

import org.springframework.lang.NonNull;

public class CommonUtils {

    @SuppressWarnings("unchecked")
    @NonNull
    public static <T> T cast(Object x) {
        return (T) x;
    }

}
