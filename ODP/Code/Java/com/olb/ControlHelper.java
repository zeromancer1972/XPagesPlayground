package com.olb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import lotus.domino.Document;
import lotus.domino.NotesException;
import lotus.domino.View;
import lotus.domino.ViewEntry;
import lotus.domino.ViewNavigator;

import com.ibm.xsp.extlib.util.ExtLibUtil;

public class ControlHelper implements Serializable {
	private static final long serialVersionUID = 3502929052852261492L;

	public List<SelectItem> getOptions() {
		View view;
		ViewNavigator nav;
		ViewEntry ent;
		ViewEntry tmp;
		Document doc;
		SelectItemGroup group = null;
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<SelectItem> items = null;
		try {
			view = ExtLibUtil.getCurrentDatabase().getView("vwRNRAll");
			nav = view.createViewNav();
			ent = nav.getFirst();
			while (ent != null) {
				tmp = nav.getNext();
				if (ent.isCategory()) {
					if (group != null) {
						SelectItem[] theList = items.toArray(new SelectItem[items.size()]);
						group.setSelectItems(theList);
						list.add(group);
					}
					group = new SelectItemGroup(ent.getColumnValues().elementAt(0).toString().trim());
					items = new ArrayList<SelectItem>();
				} else if (ent.isDocument()) {
					doc = ent.getDocument();
					items.add(new SelectItem(doc.getUniversalID(), ent.getColumnValues().elementAt(1).toString().trim()));
					doc.recycle();
				}
				ent.recycle();
				ent = tmp;
			}
			// finally place into last category
			SelectItem[] theList = items.toArray(new SelectItem[items.size()]);
			group.setSelectItems(theList);
			list.add(group);
		} catch (NotesException e) {

		}
		return list;
	}
}