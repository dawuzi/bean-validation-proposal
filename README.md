# bean-validation-proposal
A proposal for a new Java bean validation (@ValidEnumValue) implementation for adding a constraint that a value is among fixed set of string values


## Usage

Let us assume that the `TestRequest` class below is represents the request POJO of a JAX-RS resource endpoint. If we want to validate that the of `month` field of the `TestRequest` body class below is a valid month of the year based on the names of the `java.time.Month` enum values, we annotate the field as follows to add this constraint. This should be sufficient for most common use cases

```java
import java.time.Month;

import com.github.dawuzi.beanvalidation.ValidEnumValue;

public class TestRequest {
	
	@ValidEnumValue(enumClass = Month.class)
	public String month;
	
}
```

If the set of values are not the enum names directly (like the example above) but a property of an `enum` class, we simply define a class which must have a default no-argument constructor that implements the `IValidValue` interface that contains a method that defines how to retrieve the target value for each `enum` value. For example, if the constraint is based on the `sportCode` field (which can be retrieved via the `getSportCode()` method) of the `ValidSport` `enum` below. We create a simple implementation of the `IValidValue` `interface` such as `SportCodeValidValue` below. Then specify the `class` as the value of the `enumValidValueClass` field of the `@@ValidEnumValue` annotation as shown in the `TestRequest` class below

```java
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
```

```java
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
```

```java
import com.github.dawuzi.beanvalidation.ValidEnumValue;

public class TestRequest {
	
	@ValidEnumValue(enumClass = ValidSport.class, enumValidValueClass = SportCodeValidValue.class)
	public String validSportCode;
	
}
```
