package com.olb;

import java.io.Serializable;

import lotus.domino.Database;
import lotus.domino.NotesException;
import lotus.domino.View;
import lotus.domino.ViewEntryCollection;

import com.ibm.xsp.extlib.util.ExtLibUtil;

public class Viewdata implements Serializable {

	private static final long serialVersionUID = 1L;

	public ViewEntryCollection getData(String database, String view) {
		try {
			Database db = ExtLibUtil.getCurrentSession().getDatabase("", database);
			View v = db.getView(view);
			return v.getAllEntries();
		} catch (NotesException e) {
			return null;
		}
	}

}
