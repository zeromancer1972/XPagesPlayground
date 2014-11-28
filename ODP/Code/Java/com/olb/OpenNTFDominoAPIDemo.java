package com.olb;

import java.io.Serializable;

import org.openntf.domino.Database;
import org.openntf.domino.Session;
import org.openntf.domino.View;
import org.openntf.domino.ViewEntryCollection;
import org.openntf.domino.utils.XSPUtil;

public class OpenNTFDominoAPIDemo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6239457214010779186L;

	public OpenNTFDominoAPIDemo() {

	}

	public ViewEntryCollection getAllDocs() {
		Session s = XSPUtil.getCurrentSession();
		Database db = s.getCurrentDatabase();
		View v = db.getView("ByName");
		return v.getAllEntries();
	}

}
