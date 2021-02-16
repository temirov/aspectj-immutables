package net.temirov.aspectj.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation is useful for marking classes, methods, etc. for exclusion
 * from code coverage reporting and constraints. For example, data classes
 * can generally be marked with this annotation so that additional tests
 * are not required just to test the data class.
 *
 * @link https://github.com/jacoco/jacoco/wiki/FilteringOptions#annotation-based-filtering
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Generated {
    /**
     * The value element MUST have the reason we are using the annotation.
     */
    String[] value();
}
