package com.tools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lotus.domino.Database;
import lotus.domino.Document;
import lotus.domino.NotesException;
import lotus.domino.Session;
import lotus.domino.View;
import lotus.domino.ViewEntry;
import lotus.domino.ViewEntryCollection;

public class NabEntries implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<NabEntry> entries = null;

	public NabEntries(Session session) {
		entries = new ArrayList<NabEntry>();
		DatabaseSimple[] sources = { new DatabaseSimple("names.nsf", "($VIMPeople)"), new DatabaseSimple("ext42.nsf", "ByName") };

		ViewEntryCollection col = null;
		Document doc = null;
		ViewEntry ent = null;
		ViewEntry tmp = null;
		try {
			for (DatabaseSimple db : sources) {
				// System.out.println(db.getDatabasePath());
				col = this.prepareNames(session, db.getDatabasePath(), db.getViewName());
				ent = col.getFirstEntry();
				while (ent != null) {
					tmp = col.getNextEntry(ent);
					if (ent.isDocument()) {
						doc = ent.getDocument();
						entries.add(new NabEntry(doc.getItemValueString("Lastname"), doc.getItemValueString("Firstname"), doc.getItemValueString("Fullname"), doc.getUniversalID(), db));
						doc.recycle();
					}

					ent.recycle();
					ent = tmp;
				}
				if (tmp != null)
					tmp.recycle();
			}
			col.recycle();
			sources = null;

		} catch (NotesException e) {

		}
	}

	public List<NabEntry> getNames() {
		return this.entries;
	}

	private ViewEntryCollection prepareNames(Session session, String databasePath, String viewName) throws NotesException {
		Database db = session.getDatabase(session.getCurrentDatabase().getServer(), databasePath);
		View view = db.getView(viewName);

		return view.getAllEntries();
	}

}
