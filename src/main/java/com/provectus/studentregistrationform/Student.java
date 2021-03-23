package com.provectus.studentregistrationform;

public final class Student {
	
	private final String firstName;
	private final String lastName;
	private final String phoneNumber;
	private final Gender gender;
	
	
	public Student(String firstName, String lastName, Gender g, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.gender = g;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

}
