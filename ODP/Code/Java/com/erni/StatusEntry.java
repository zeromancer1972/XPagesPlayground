package com.erni;

public class StatusEntry {

	/*
	 * Datenmodell
	 */

	private final String linkText;
	private final String linkPage;
	private final String linkStatus;

	public StatusEntry(String linkText, String linkPage, String linkStatus) {
		this.linkText = linkText;
		this.linkPage = linkPage;
		this.linkStatus = linkStatus;
	}

	public String getLinkText() {
		return linkText;
	}

	public String getLinkPage() {
		return linkPage;
	}

	public String getLinkStatus() {
		return linkStatus;
	}

}
