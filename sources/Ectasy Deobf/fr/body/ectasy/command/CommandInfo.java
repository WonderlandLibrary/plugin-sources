package fr.body.ectasy.command;

import fr.body.ectasy.user.Rank;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CommandInfo {
   String name();

   boolean blatant();

   Rank rank();

   String description();

   String[] aliases() default {};
}
