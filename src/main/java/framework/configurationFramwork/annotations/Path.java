package framework.configurationFramwork.annotations;

import framework.configurationFramwork.enumerations.HttpMethods;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Path {
    String value() default "";

    HttpMethods method() default HttpMethods.GET;
}
