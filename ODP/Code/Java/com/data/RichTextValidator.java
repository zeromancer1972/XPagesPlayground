package com.data;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;

import lotus.domino.NotesException;

import com.ibm.xsp.extlib.util.ExtLibUtil;
import com.ibm.xsp.model.domino.wrapped.DominoDocument;
import com.ibm.xsp.model.domino.wrapped.DominoRichTextItem;
import com.ibm.xsp.model.domino.wrapped.DominoDocument.AttachmentValueHolder;

public class RichTextValidator implements Serializable {

	private static final long serialVersionUID = 9050983095311756377L;
	private final boolean debug = true;
	private boolean empty;
	private boolean attachments;

	public RichTextValidator() {
		this.empty = true;
		this.attachments = false;
	}

	public void validate(final DominoDocument xspdoc, final String itemName) throws NotesException {
		try {
			DominoRichTextItem rtf = (DominoRichTextItem) xspdoc.getValue(itemName);

			// Test for content
			if (rtf == null) {
				log("RTF is NULL");
				empty = true;
				attachments = false;
			} else {
				String html = rtf.getHTML().replaceAll("<[^>]*>&#?[^;]+;", "").trim();
				log("HTML: " + html);
				empty = html.equals("");

				// Test for attachments
				List<AttachmentValueHolder> atts = rtf.getAttachmentList(FacesContext.getCurrentInstance());
				if (atts != null)
					attachments = atts.size() > 0;
			}

			// Finally put some info in the scope
			ExtLibUtil.getViewScope().put(itemName + "_isEmpty", empty);
			ExtLibUtil.getViewScope().put(itemName + "_isAttachments", attachments);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public boolean isEmpty() {
		return empty;
	}

	public boolean isAttachments() {
		return attachments;
	}

	private void log(final String msg) {
		if (debug)
			System.out.println(msg);
	}

}