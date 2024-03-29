package com.github.dawuzi.beanvalidation.helper;

import com.github.dawuzi.beanvalidation.IValidValue;

public class SportCodeValidValue implements IValidValue {

	@Override
	public String getValidEnumValue(Enum<?> enumValue) {
		
		if(enumValue == null) {
			return null;
		}
		
//		it is not of the ValidSport enum class so lets just return the name
		if(!ValidSport.class.equals(enumValue.getClass())) {
			return enumValue.name();
		}
		
		return ((ValidSport) enumValue).getSportCode();
	}

}
