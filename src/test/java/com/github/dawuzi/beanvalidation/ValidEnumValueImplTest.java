package com.github.dawuzi.beanvalidation;

import java.time.Month;

public class ValidEnumValueImplTest {

	public static void main(String[] args) {
		
		ValidEnumValueImpl validEnumStringImpl = new ValidEnumValueImpl();
		
		validEnumStringImpl.enumClass = Month.class;
	}
	
}
