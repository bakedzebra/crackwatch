package com.vicefix.crackwatch.http.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProgressStart {
    String title() default "Processing";

    int max() default -1;

    String extraMessage() default "";
}