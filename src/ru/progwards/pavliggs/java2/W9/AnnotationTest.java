package ru.progwards.pavliggs.java2.W9;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AnnotationTest {
    String text() default "Всегда говори привет";
}
