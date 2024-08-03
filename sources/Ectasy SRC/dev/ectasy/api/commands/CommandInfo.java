package dev.ectasy.api.commands;

import dev.ectasy.api.data.Rank;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(java.lang.annotation.ElementType.TYPE)
public @interface CommandInfo {
    String name();
    String description();
    Rank rank();
    boolean blatant() default false;
    String[] aliases() default {};
    boolean async() default true;
    boolean listener() default false;


}
