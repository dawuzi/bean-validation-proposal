package com.github.dawuzi.beanvalidation;

import java.lang.reflect.Field;
import java.time.Month;

import org.junit.Assert;
import org.junit.Test;

import com.github.dawuzi.beanvalidation.helper.TestRequest;
import com.github.dawuzi.beanvalidation.helper.ValidSport;

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
		
		String[] monthValidValues = validEnumStringImpl.validValues;
		
		String[] expectedMonthValues = new String[Month.values().length];
		
		for (int i = 0; i < Month.values().length; i++) {
			expectedMonthValues[i] = Month.values()[i].name();
		}
		
		Assert.assertArrayEquals(expectedMonthValues, monthValidValues);
		
		
		
		Field validSportNameField = getField(clazz, "validSportName");
		
		ValidEnumValue validSportNameFieldValidEnumValue = validSportNameField.getAnnotation(ValidEnumValue.class);
		
		validEnumStringImpl.initialize(validSportNameFieldValidEnumValue);
		
		String[] validSportNameValidValues = validEnumStringImpl.validValues;
		
		String[] expectedValidSportNameValues = new String[ValidSport.values().length];
		
		for (int i = 0; i < ValidSport.values().length; i++) {
			expectedValidSportNameValues[i] = ValidSport.values()[i].name();
		}
		
		Assert.assertArrayEquals(expectedValidSportNameValues, validSportNameValidValues);
		
		
		
		Field validSportCodeField = getField(clazz, "validSportCode");
		
		ValidEnumValue validSportCodeFieldValidEnumValue = validSportCodeField.getAnnotation(ValidEnumValue.class);
		
		validEnumStringImpl.initialize(validSportCodeFieldValidEnumValue);
		
		String[] validSportCodeValidValues = validEnumStringImpl.validValues;
		
		String[] expectedValidSportCodeValues = new String[ValidSport.values().length];
		
		for (int i = 0; i < ValidSport.values().length; i++) {
			expectedValidSportCodeValues[i] = ValidSport.values()[i].getSportCode();
		}
		
		Assert.assertArrayEquals(expectedValidSportCodeValues, validSportCodeValidValues);
		
	}

	private Field getField(Class<TestRequest> clazz, String fieldName) {
		try {
			return clazz.getField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			throw new IllegalArgumentException("Error getting field name : "+fieldName, e);
		}
	}
	
}
