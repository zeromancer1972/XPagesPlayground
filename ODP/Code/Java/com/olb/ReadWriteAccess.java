package com.olb;

import java.io.Serializable;
import java.util.Vector;

import lotus.domino.Document;
import lotus.domino.Item;
import lotus.domino.NotesException;

public class ReadWriteAccess implements Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public void save(Document doc, boolean saveIt) {
		try {
			// check if special items exist
			if (!doc.hasItem("$rnaReaders") && !doc.hasItem("$rnaAuthors"))
				return;

			// now fetch all the items by name and set the property
			Vector v = doc.getItemValue("$rnaReaders");
			for (int x = 0; x < v.size(); x++) {
				Item it = doc.getFirstItem(v.elementAt(x).toString());
				if (it != null) {
					if (!it.isReaders())
						it.setReaders(true);
				}
				it.recycle();
			}
			v = doc.getItemValue("$rnaAuthors");
			for (int x = 0; x < v.size(); x++) {
				Item it = doc.getFirstItem(v.elementAt(x).toString());
				if (it != null) {
					if (!it.isAuthors())
						it.setAuthors(true);
				}
				it.recycle();
			}

			if (saveIt) {
				doc.save();
			}

		} catch (NotesException e) {
			e.printStackTrace();
			try {
				if (saveIt) {
					doc.replaceItemValue("$rnaError", e.getMessage());
					doc.save();
				}
			} catch (Exception e2) {
				
			}
		}
	}
}
