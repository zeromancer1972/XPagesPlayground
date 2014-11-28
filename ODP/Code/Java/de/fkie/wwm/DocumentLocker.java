package de.fkie.wwm;

import java.io.Serializable;
import java.util.Date;

import lotus.domino.Database;
import lotus.domino.DateTime;
import lotus.domino.Document;
import lotus.domino.Item;
import lotus.domino.Session;
import lotus.domino.View;

import com.ibm.xsp.extlib.util.ExtLibUtil;

public class DocumentLocker implements Serializable {

	private static final long serialVersionUID = 6864140212219448685L;

	private String lockUNID;
	private String lockUser;
	private boolean locked;
	private boolean newNote;
	private boolean editable;
	private boolean already;

	public DocumentLocker(Document doc, boolean editable) {
		try {
			this.newNote = doc.isNewNote();
			this.editable = editable;
			this.already = false;
			Session session = ExtLibUtil.getCurrentSession();
			this.lockUser = session.getEffectiveUserName();
			Database db = ExtLibUtil.getCurrentDatabase();
			View v = db.getView("vwLockedDocuments");
			Document lockdoc = v.getDocumentByKey(doc.getUniversalID(), true);
			if (lockdoc == null) {
				this.locked = false;
				this.lockUNID = doc.getUniversalID();
			} else {
				this.locked = true;
				String u = lockdoc.getItemValueString("lockUser");
				if (u.equals(lockUser)) {
					this.already = true;

				}
				this.lockUNID = lockdoc.getUniversalID();
			}
			lockdoc.recycle();
		} catch (Exception e) {

		}
	}

	public boolean isLocked() {
		return this.locked;
	}

	public void lock() {
		if (this.locked || this.newNote)
			return;
		if (!this.editable)
			return;
		if (this.already)
			return;

		this.locked = true;
		// document is not locked, so lock it
		try {
			Session session = ExtLibUtil.getCurrentSession();
			Database db = ExtLibUtil.getCurrentDatabase();
			Document doc = db.createDocument();
			doc.replaceItemValue("Form", "frmLock");
			doc.replaceItemValue("lockUNID", this.lockUNID);
			Item it = doc.replaceItemValue("lockUser", this.lockUser);
			it.setAuthors(true);
			DateTime dt = session.createDateTime(new Date());
			doc.replaceItemValue("lockTimestamp", dt);
			doc.save();
			doc.recycle();
		} catch (Exception e) {
		}
	}

	public void unlock() {
		if (!this.locked)
			return;
		if (!this.editable)
			return;
		this.locked = false;
		// document is locked, remove lock
		try {
			Session session = ExtLibUtil.getCurrentSessionAsSigner();
			Database db = session.getCurrentDatabase();
			Document doc = db.getDocumentByUNID(this.lockUNID);
			if (doc != null) {
				if (doc.getItemValueString("lockUser").equals(this.lockUser)) {
					doc.remove(true);
				}

				doc.recycle();
			}

		} catch (Exception e) {

		}

	}

}
