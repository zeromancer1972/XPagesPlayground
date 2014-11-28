package com.olb;

import java.io.Serializable;

import lotus.domino.NotesException;

import com.ibm.commons.util.StringUtil;
import com.ibm.xsp.application.ApplicationEx;
import com.ibm.xsp.extlib.util.ExtLibUtil;

public class XSPProps implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String getXspProperty(String propertyName, String defaultValue) {
		String retVal = ApplicationEx.getInstance().getApplicationProperty(
				propertyName, getIniVar(propertyName, defaultValue));
		return retVal;
	}

	public static String getIniVar(String propertyName, String defaultValue) {
		try {
			String newVal = ExtLibUtil.getCurrentSession()
					.getEnvironmentString(propertyName, true);
			if (StringUtil.isEmpty(newVal)) {
				return newVal;
			} else {
				return defaultValue;
			}
		} catch (NotesException e) {

			return defaultValue;
		}
	}
}
