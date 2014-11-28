package com.erni;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StatusEntries implements Serializable {

	private static final long serialVersionUID = 6824258781539298953L;

	public List<StatusEntry> getEntries() {
		List<StatusEntry> liste = new ArrayList<StatusEntry>();
		liste.add(new com.erni.StatusEntry("bean1", "erni1.xsp", "test"));
		liste.add(new com.erni.StatusEntry("bean2", "erni2.xsp", "schnuck"));
		return liste;
	}
}
