package minevalley.smart.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Description {

    String name();

    // can be omitted -> use of name()
    String niceName() default "";

    String version() default "1.0";

    String[] authors();

    String iconHead();

    int price() default 0;

    boolean preInstalled() default false;

    int priority() default 0;
}
