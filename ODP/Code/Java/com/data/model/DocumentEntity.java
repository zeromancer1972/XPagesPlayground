package com.data.model;

import java.io.Serializable;

import lotus.domino.DateTime;

import org.openntf.xpt.core.dss.annotations.DominoEntity;
import org.openntf.xpt.core.dss.annotations.DominoStore;

@DominoStore(Form = "frmDocument", View = "DocumentsById", PrimaryKeyField = "ID", PrimaryFieldClass = String.class)
public class DocumentEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@DominoEntity(FieldName = "ID")
	private String id;
	@DominoEntity(FieldName = "Created")
	private DateTime created;
	@DominoEntity(FieldName = "CreatedBy")
	private String createdBy;
	@DominoEntity(FieldName = "content")
	private String content;

	public synchronized String getId() {
		return id;
	}

	public synchronized void setId(String id) {
		this.id = id;
	}

	public synchronized DateTime getCreated() {
		return created;
	}

	public synchronized void setCreated(DateTime created) {
		this.created = created;
	}

	public synchronized String getCreatedBy() {
		return createdBy;
	}

	public synchronized void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public synchronized String getContent() {
		return content;
	}

	public synchronized void setContent(String content) {
		this.content = content;
	}

}
