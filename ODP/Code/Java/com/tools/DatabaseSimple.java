package com.tools;

public class DatabaseSimple {
	private final String databasePath;
	private final String viewName;

	public DatabaseSimple(String databasePath, String viewName) {
		this.databasePath = databasePath;
		this.viewName = viewName;
	}

	public String getDatabasePath() {
		return databasePath;
	}

	public String getViewName() {
		return viewName;
	}

}
