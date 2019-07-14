/**
 * 
 */
package com.github.dawuzi.beanvalidation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

/**
 * This custom validator ensures that a string is valid value based on values of a specified Enum class<br>
 * This was written based on JSR 349. Bean Validation 1.1<br>
 * <p>
 * null values are considered valid
 * 
 * @author Okafor Ezewuzie
 *
 */
@Documented
@Constraint(validatedBy = {ValidEnumValueImpl.class})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface ValidEnumValue {
	
	String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	/**
	 * 
	 * @return The enum {@link Class} that determines whose values determine what strings are valid
	 */
	Class<? extends Enum<?>> enumClass();
	
	/**
	 * 
	 * @return {@link Class} which will contain a specific implementation when the name of the enum is not to be used as
	 * valid list of strings e.g. when field values of the enum are the target list of valid strings. Null is valid in which
	 * case the name of the enum values are used as the valid list of strings
	 * 
	 */
	Class<? extends IValidValue> enumValidValueClass();
}
