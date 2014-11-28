package com.data;

import java.io.Serializable;

import lotus.domino.Document;
import lotus.domino.NotesException;

import com.ibm.xsp.extlib.util.ExtLibUtil;
import com.ibm.xsp.model.domino.wrapped.DominoDocument;

public class DocumentValidator implements Serializable {

	/**
	 * Members
	 */
	private static final long serialVersionUID = 8735908139486069178L;

	public DocumentValidator() {

	}

	public boolean validate(DominoDocument xspdoc) throws NotesException {
		Document doc = xspdoc.getDocument(true);
		boolean result = true;
		double numberValue = 0;
		try {
			numberValue = doc.getItemValueDouble("numbervalue");

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (numberValue <= 0) {
			result = false;
		}

		String resultText = result ? "passed" : "not passed";
		System.out.println(resultText);
		ExtLibUtil.getViewScope().put("validationResult", "Result for " + numberValue + ": " + resultText);
		return result;
	}
}
