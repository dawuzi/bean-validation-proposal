package com.github.dawuzi.beanvalidation;

import java.lang.reflect.Field;
import java.time.Month;

import org.junit.Assert;
import org.junit.Test;

import com.github.dawuzi.beanvalidation.helper.TestRequest;

/**
 * 
 * @author Okafor Ezewuzie
 *
 */
public class ValidEnumValueImplTest {

	public static void main(String[] args) {
		
		ValidEnumValueImpl validEnumStringImpl = new ValidEnumValueImpl();
		
		validEnumStringImpl.enumClass = Month.class;
	}
	
	@Test
	public void testInitialize() {
		
		ValidEnumValueImpl validEnumStringImpl = new ValidEnumValueImpl();
		
		Class<TestRequest> clazz = TestRequest.class;
		
		Field monthField = getField(clazz, "month");
		
		ValidEnumValue monthFieldValidEnumValue = monthField.getAnnotation(ValidEnumValue.class);
		
		validEnumStringImpl.initialize(monthFieldValidEnumValue);
		
		String[] validValues = validEnumStringImpl.validValues;
		
		String[] expected = new String[Month.values().length];
		
		for (int i = 0; i < Month.values().length; i++) {
			expected[i] = Month.values()[i].name();
		}
		
		Assert.assertArrayEquals(expected, validValues);
		
	}

	private Field getField(Class<TestRequest> clazz, String fieldName) {
		try {
			return clazz.getField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			throw new IllegalArgumentException("Error getting field name : "+fieldName, e);
		}
	}
	
}
