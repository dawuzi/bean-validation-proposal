package com.github.dawuzi.beanvalidation.helper;

import java.time.DayOfWeek;
import java.time.Month;

import com.github.dawuzi.beanvalidation.ValidEnumValue;

public class TestRequest {
	
	@ValidEnumValue(enumClass = Month.class)
	public String month;
	
	@ValidEnumValue(enumClass = ValidSport.class)
	public String validSportName;
	
	@ValidEnumValue(enumClass = ValidSport.class, enumValidValueClass = SportCodeValidValue.class)
	public String validSportCode;
	
	@ValidEnumValue(enumClass = DayOfWeek.class, enumValidValueClass = MissingNoargConstructorValidValueImpl.class)
	public String dayOfWeek;
	
}
