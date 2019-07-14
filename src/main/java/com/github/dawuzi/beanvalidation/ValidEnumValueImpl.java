package com.github.dawuzi.beanvalidation;

import java.util.Collection;
import java.util.logging.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

/**
 * This is the concrete validator class for the {@link ValidEnumValue} constraint annotation 
 * 
 * @author Okafor Ezewuzie
 *
 */
public class ValidEnumValueImpl implements ConstraintValidator<ValidEnumValue, Object> {
	
	private static final Logger log = Logger.getLogger(ValidEnumValueImpl.class.getName());

	Class<? extends Enum<?>> enumClass;
	String[] validValues;
	
	@Override
	public void initialize(ValidEnumValue constraintAnnotation) {
		
		enumClass = constraintAnnotation.enumClass();
		
		Class<? extends IValidValue> enumValueFunctionClass = constraintAnnotation.enumValidValueClass();
		
		IValidValue validValue = null;
		
		if(enumValueFunctionClass != null) {
			
			try {
				validValue = enumValueFunctionClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				
				log.severe( () -> "Cannot create an instance using the default no argument constructor of the enumValidValueClass : " 
						+ enumValueFunctionClass.getName());
				
				throw new IllegalArgumentException("Error invoking default no argument constructor on the enumValueFunctionClass : " 
						+ enumValueFunctionClass.getName(), e);
			}
		} 
		
		Enum<?>[] enumConstants = enumClass.getEnumConstants();

		validValues = new String[enumConstants.length];

		for (int i = 0; i < enumConstants.length; i++) {
			
			Enum<?> localEnumValue = enumConstants[i];
			
			if(validValue == null) {
				validValues[i] = localEnumValue.name();
			} else {
				validValues[i] = validValue.getValidEnumValue(localEnumValue);
			}
		}
		
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		if(value == null){
			return true;
		}
		
		boolean valid = isValidObject(value);
		
		boolean isDefaultMessage = "".equals(context.getDefaultConstraintMessageTemplate());
		
		if(!valid && isDefaultMessage){
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("Invalid ")
			.append(enumClass.getSimpleName())
			.append(" value specified. Valid values are : ")
			.append(String.join(" ", validValues));
			
			ConstraintViolationBuilder constraintViolationBuilder = context.buildConstraintViolationWithTemplate(sb.toString());
			
			constraintViolationBuilder.addConstraintViolation();
		}
		
		return valid;
	}
	
	private boolean isValidObject(Object value){
		
		boolean valid = true;
		
		if(value.getClass().isArray()){
//			so it is an array of objects not primitives
			if(!value.getClass().getComponentType().isPrimitive()){
				
				Object[] values = (Object[]) value;
				
				for(Object aValue : values){
					if(aValue == null){
						continue;
					}
					if(!isValidValueString(aValue.toString())){
						valid = false;
						break;
					}
				}
			} else {
//				we only validate objects and not primitives
				valid = false;
			}
		} else if (Collection.class.isAssignableFrom(value.getClass())){
			
			@SuppressWarnings("unchecked")
			Collection<Object> values = (Collection<Object>) value;
			
			for(Object aValue : values){
				if(aValue == null){
					continue;
				}
				if(!isValidValueString(aValue.toString())){
					valid = false;
					break;
				}
			}
		} else {
//			we are assuming is an object
			if(!isValidValueString(value.toString())){
				valid = false;
			}
		}
		
		return valid;
	}
	
	private boolean isValidValueString(String value) {
		
		for (String localValidEnumValue : validValues) {
			
			if(value.equals(localValidEnumValue)){
				return true;
			}
		}
		
		return false;
	}
	
}