package com.namics.oss.magnolia.appbuilder.ui5.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface AppFactory {

	String id();

	String name();

	String label();

	String icon();

	String i18nBasename() default "";

	String theme() default "";

	boolean isEnabled() default true;
}
