package com.olb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;

import lotus.domino.Database;
import lotus.domino.Name;
import lotus.domino.NotesException;
import lotus.domino.Session;


public class UserBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private final List<String> aclLevelNames = new ArrayList<String>();

	private Session session = null;
	private int aclLevel;
	private String aclLevelName;
	private String userNameCommon;
	private String userNameAbbreviated;
	private String userNameCanonical;
	private String userRoles;
	private String emailAdress;
	private String mailFilePath;
	private boolean canEdit;
	private boolean canDelete;

	public UserBean() {
		this.init();
	}

	public void init() {
		
		session = getCurrentSession();
		
		try {

			aclLevelNames.add("ACL.LEVEL_NO_ACCESS");
			aclLevelNames.add("ACL.LEVEL_DEPOSITOR");
			aclLevelNames.add("ACL.LEVEL_READER");
			aclLevelNames.add("ACL.LEVEL_AUTHOR");
			aclLevelNames.add("ACL.LEVEL_EDITOR");
			aclLevelNames.add("ACL.LEVEL_DESIGNER");
			aclLevelNames.add("ACL.LEVEL_MANAGER");

			Name name = session.createName(session.getEffectiveUserName());
			this.userNameCommon = name.getCommon();
			this.userNameAbbreviated = name.getAbbreviated();
			this.userNameCanonical = name.getCanonical();
			this.emailAdress = (String) session.evaluate("@NameLookup( [Exhaustive] ; \"" + session.getEffectiveUserName() + "\"; \"InternetAddress\")")
					.elementAt(0);
			this.mailFilePath = (String) session.evaluate("@NameLookup( [Exhaustive] ; \"" + session.getEffectiveUserName() + "\"; \"MailFile\")").elementAt(0);
			this.aclLevel = session.getCurrentDatabase().queryAccess(this.userNameCanonical);
			this.aclLevelName = aclLevelNames.get(this.aclLevel);
			this.userRoles = join(session.getCurrentDatabase().queryAccessRoles(this.userNameCanonical), "; ");
			this.canEdit = aclLevel > 2;
			this.canDelete = (session.getCurrentDatabase().queryAccessPrivileges(this.userNameCanonical) & Database.DBACL_DELETE_DOCS) > 0;

			// System.out.println(this.userNameCanonical);

		} catch (NotesException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean getCanDelete() {
		return canDelete;
	}

	public boolean getCanEdit() {
		return canEdit;
	}

	public String getUserNameAbbreviated() {
		return userNameAbbreviated;
	}

	public String getUserNameCanonical() {
		return userNameCanonical;
	}

	public String getAclLevelName() {
		return aclLevelName;
	}

	public String getEmailAdress() {
		return emailAdress;
	}

	public String getMailFilePath() {
		return mailFilePath;
	}

	public int getAclLevel() {
		return aclLevel;
	}

	public String getUserNameCommon() {
		return userNameCommon;
	}

	public String getUserRoles() {
		return userRoles;
	}

	@SuppressWarnings("unchecked")
	public static String join(Collection s, String delimiter) {
		StringBuffer buffer = new StringBuffer();
		Iterator iter = s.iterator();
		while (iter.hasNext()) {
			buffer.append(iter.next());
			if (iter.hasNext()) {
				buffer.append(delimiter);
			}
		}
		return buffer.toString();
	}

	public boolean hasRole(String roleName) {
		return (this.userRoles.indexOf(roleName) != -1);
	}

	public static Session getCurrentSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (Session) context.getApplication().getVariableResolver().resolveVariable(context, "session");
		
//		return com.ibm.xsp.extlib.util.ExtLibUtil.getCurrentSession();
		
	}
}
