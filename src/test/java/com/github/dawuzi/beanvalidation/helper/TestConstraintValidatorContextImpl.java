package com.github.dawuzi.beanvalidation.helper;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder.LeafNodeBuilderCustomizableContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderDefinedContext;

public class TestConstraintValidatorContextImpl implements ConstraintValidatorContext {

	@Override
	public void disableDefaultConstraintViolation() {

	}

	@Override
	public String getDefaultConstraintMessageTemplate() {
		return "";
	}

	@Override
	public ConstraintViolationBuilder buildConstraintViolationWithTemplate(String messageTemplate) {
		return new ConstraintViolationBuilder() {
			
			@Override
			public NodeBuilderCustomizableContext addPropertyNode(String name) {
				return null;
			}
			
			@Override
			public NodeBuilderDefinedContext addParameterNode(int index) {
				return null;
			}
			
			@Override
			public NodeBuilderDefinedContext addNode(String name) {
				return null;
			}
			
			@Override
			public ConstraintValidatorContext addConstraintViolation() {
				return null;
			}
			
			@Override
			public LeafNodeBuilderCustomizableContext addBeanNode() {
				return null;
			}
		};
	}

	@Override
	public <T> T unwrap(Class<T> type) {
		return null;
	}

}
