package com.github.dawuzi.beanvalidation.helper;

import com.github.dawuzi.beanvalidation.IValidValue;

public class MissingNoargConstructorValidValueImpl implements IValidValue {
	
	protected final String value;
	
	public MissingNoargConstructorValidValueImpl(String value) {
		this.value = value;
	}

	@Override
	public String getValidEnumValue(Enum<?> enumValue) {
		return enumValue == null ? null : enumValue.name();
	}
	
}
