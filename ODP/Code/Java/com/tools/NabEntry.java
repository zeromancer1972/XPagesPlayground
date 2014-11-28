package com.tools;

public class NabEntry {
	private final String lastName;
	private final String firstName;
	private final String fullName;
	private final String universalId;
	private final DatabaseSimple db;

	public NabEntry(String lastName, String firstName, String fullName, String universalId, DatabaseSimple db) {
		System.out.println(lastName + ", " + firstName + ", " + fullName + ", " + universalId);
		this.lastName = lastName;
		this.firstName = firstName;
		this.fullName = fullName;
		this.universalId = universalId;
		this.db = db;
	}

	public DatabaseSimple getDb() {
		return db;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getFullName() {
		return fullName;
	}

	public String getUniversalId() {
		return universalId;
	}
}
