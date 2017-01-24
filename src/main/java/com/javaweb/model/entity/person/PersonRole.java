package com.javaweb.model.entity.person;

import java.util.Arrays;
import java.util.Optional;

public enum PersonRole {
	TUTOR,
	STUDENT;
	
	public static Optional<PersonRole> getRoleFromString(String roleString){
		Optional<PersonRole> role = Arrays.stream(PersonRole.values())
				.filter(elem -> elem.toString().equals(roleString)).findAny();
		return role;
	}
}
