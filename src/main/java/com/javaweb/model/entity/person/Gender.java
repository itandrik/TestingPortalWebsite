package com.javaweb.model.entity.person;

import java.util.Arrays;
import java.util.Optional;

public enum Gender {
	MALE,
	FEMALE;
		
	public static Optional<Gender> getGenderFromString(String genderString){
		return Arrays.stream(Gender.values())
				.filter(elem -> elem.toString().equals(genderString)).findAny();
	}
}
