package com.github.dawuzi.beanvalidation.helper;

public enum ValidSport {

	BOXING("BXG"),
	CYCLING("CYC"),
	FOOTBALL("FBL"),
	BASKETBALL("BKB"),
	SWIMMING("SWM"),
	;
	
	private final String sportCode;
	
	private ValidSport(String sportCode) {
		this.sportCode = sportCode;
	}

	public String getSportCode() {
		return sportCode;
	}
	
}
