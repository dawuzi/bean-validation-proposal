package com.github.dawuzi.beanvalidation;

import java.lang.reflect.Field;
import java.time.Month;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.github.dawuzi.beanvalidation.helper.TestConstraintValidatorContextImpl;
import com.github.dawuzi.beanvalidation.helper.TestRequest;
import com.github.dawuzi.beanvalidation.helper.ValidSport;

/**
 * 
 * @author Okafor Ezewuzie
 *
 */
public class ValidEnumValueImplTest {

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
		
		
		Field dayOfWeekField = getField(clazz, "dayOfWeek");
		
		ValidEnumValue dayOfWeekFieldFieldValidEnumValue = dayOfWeekField.getAnnotation(ValidEnumValue.class);
		
		try {
			validEnumStringImpl.initialize(dayOfWeekFieldFieldValidEnumValue);
			Assert.fail("intialise with a no arg constructor IValidValue class should throw an Exception");
		} catch (Exception ignored) {
		}
		
	}

	private Field getField(Class<TestRequest> clazz, String fieldName) {
		try {
			return clazz.getField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			throw new IllegalArgumentException("Error getting field name : "+fieldName, e);
		}
	}
	
	@Test
	public void testIsValid() {
		
		ValidEnumValueImpl validEnumStringImpl = new ValidEnumValueImpl();
		
		Class<TestRequest> clazz = TestRequest.class;
		
		Field monthField = getField(clazz, "month");
		
		ValidEnumValue monthFieldValidEnumValue = monthField.getAnnotation(ValidEnumValue.class);
		
		validEnumStringImpl.initialize(monthFieldValidEnumValue);
		
		Assert.assertTrue(validEnumStringImpl.isValid(null, new TestConstraintValidatorContextImpl()));
		Assert.assertTrue(validEnumStringImpl.isValid("JANUARY", new TestConstraintValidatorContextImpl()));
		Assert.assertTrue(validEnumStringImpl.isValid("FEBRUARY", new TestConstraintValidatorContextImpl()));
		Assert.assertFalse(validEnumStringImpl.isValid("JAN", new TestConstraintValidatorContextImpl()));
		
	}
	
	@Test
	public void testIsValidObject() {
		
		ValidEnumValueImpl validEnumStringImpl = new ValidEnumValueImpl();
		
		Class<TestRequest> clazz = TestRequest.class;
		
		Field monthField = getField(clazz, "month");
		
		ValidEnumValue monthFieldValidEnumValue = monthField.getAnnotation(ValidEnumValue.class);
		
		validEnumStringImpl.initialize(monthFieldValidEnumValue);
		
		Assert.assertTrue(validEnumStringImpl.isValidObject("JANUARY"));
		Assert.assertTrue(validEnumStringImpl.isValidObject("MARCH"));
		Assert.assertTrue(validEnumStringImpl.isValidObject(new String[] {"JANUARY", "FEBRUARY"}));
		Assert.assertTrue(validEnumStringImpl.isValidObject(Arrays.asList("JANUARY", "FEBRUARY")));
		Assert.assertTrue(validEnumStringImpl.isValidObject(new String[] {"MARCH", "APRIL", "DECEMBER"}));
		Assert.assertTrue(validEnumStringImpl.isValidObject(Arrays.asList("MARCH", "APRIL", "DECEMBER")));

		Assert.assertFalse(validEnumStringImpl.isValidObject(new int[] {1, 2, 3}));
		Assert.assertFalse(validEnumStringImpl.isValidObject("APR"));
		Assert.assertFalse(validEnumStringImpl.isValidObject("MAR"));
		Assert.assertFalse(validEnumStringImpl.isValidObject(new String[] {"JANUARY", "FEB"}));
		Assert.assertFalse(validEnumStringImpl.isValidObject(Arrays.asList("JAN", "FEBRUARY")));
		Assert.assertFalse(validEnumStringImpl.isValidObject(new String[] {"MAR", "APRIL", "DEC"}));
		Assert.assertFalse(validEnumStringImpl.isValidObject(Arrays.asList("MAR", "NOV", "DEC")));
		
		
		Field validSportNameField = getField(clazz, "validSportName");
		
		ValidEnumValue validSportNameFieldValidEnumValue = validSportNameField.getAnnotation(ValidEnumValue.class);
		
		validEnumStringImpl.initialize(validSportNameFieldValidEnumValue);
		
		Assert.assertTrue(validEnumStringImpl.isValidObject("BOXING"));
		Assert.assertTrue(validEnumStringImpl.isValidObject("CYCLING"));
		Assert.assertTrue(validEnumStringImpl.isValidObject(new String[] {"BOXING", "CYCLING"}));
		Assert.assertTrue(validEnumStringImpl.isValidObject(Arrays.asList("FOOTBALL", "BASKETBALL")));
		Assert.assertTrue(validEnumStringImpl.isValidObject(Arrays.asList("FOOTBALL", "BASKETBALL", null)));
		Assert.assertTrue(validEnumStringImpl.isValidObject(new String[] {"BOXING", "FOOTBALL", "SWIMMING"}));
		Assert.assertTrue(validEnumStringImpl.isValidObject(new String[] {"BOXING", "FOOTBALL", null, "SWIMMING"}));
		Assert.assertTrue(validEnumStringImpl.isValidObject(Arrays.asList("SWIMMING", "BASKETBALL")));

		Assert.assertFalse(validEnumStringImpl.isValidObject("LAUGHING"));
		Assert.assertFalse(validEnumStringImpl.isValidObject("WHISTLING"));
		Assert.assertFalse(validEnumStringImpl.isValidObject(new String[] {"CHEWING", "WHISTLING"}));
		Assert.assertFalse(validEnumStringImpl.isValidObject(Arrays.asList("CHEWING", "WHISTLING")));
		Assert.assertFalse(validEnumStringImpl.isValidObject(new String[] {"LAUGHING", "CHEWING", "WHISTLING"}));
		Assert.assertFalse(validEnumStringImpl.isValidObject(Arrays.asList("LAUGHING", "CHEWING", "WHISTLING")));
		
		
		Field validSportCodeField = getField(clazz, "validSportCode");
		
		ValidEnumValue validSportCodeFieldValidEnumValue = validSportCodeField.getAnnotation(ValidEnumValue.class);
		
		validEnumStringImpl.initialize(validSportCodeFieldValidEnumValue);
		
		Assert.assertTrue(validEnumStringImpl.isValidObject("BXG"));
		Assert.assertTrue(validEnumStringImpl.isValidObject("CYC"));
		Assert.assertTrue(validEnumStringImpl.isValidObject(new String[] {"BXG", "CYC"}));
		Assert.assertTrue(validEnumStringImpl.isValidObject(new String[] {"BXG", "CYC"}));
		Assert.assertTrue(validEnumStringImpl.isValidObject(Arrays.asList("FBL", "BKB")));
		Assert.assertTrue(validEnumStringImpl.isValidObject(Arrays.asList("FBL", "BKB", null)));
		Assert.assertTrue(validEnumStringImpl.isValidObject(new String[] {"BXG", "FBL", "SWM"}));
		Assert.assertTrue(validEnumStringImpl.isValidObject(new String[] {"BXG", "FBL", "SWM", null}));
		Assert.assertTrue(validEnumStringImpl.isValidObject(Arrays.asList("SWM", "BKB")));

		Assert.assertFalse(validEnumStringImpl.isValidObject("BOXING"));
		Assert.assertFalse(validEnumStringImpl.isValidObject("CYCLING"));
		Assert.assertFalse(validEnumStringImpl.isValidObject(new String[] {"CHEWING", "WHISTLING"}));
		Assert.assertFalse(validEnumStringImpl.isValidObject(Arrays.asList("CHEWING", "WHISTLING")));
		Assert.assertFalse(validEnumStringImpl.isValidObject(new String[] {"LAUGHING", "CHEWING", "WHISTLING"}));
		Assert.assertFalse(validEnumStringImpl.isValidObject(Arrays.asList("LAUGHING", "CHEWING", "WHISTLING")));

	}
	
}
