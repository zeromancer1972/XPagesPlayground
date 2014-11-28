package com.data.model;

import java.util.Date;
import java.util.UUID;

import org.openntf.domino.utils.XSPUtil;
import org.openntf.xpt.core.dss.AbstractStorageService;

public class DocumentStorageService extends AbstractStorageService<DocumentEntity> {

	private static DocumentStorageService m_Service;

	public DocumentStorageService() {

	}

	public static synchronized DocumentStorageService getInstance() {
		if (m_Service == null) {
			m_Service = new DocumentStorageService();
		}

		return m_Service;
	}

	@Override
	protected DocumentEntity createObject() {
		DocumentEntity ent = new DocumentEntity();
		ent.setId(UUID.randomUUID().toString());
		ent.setCreated(XSPUtil.getCurrentSession().createDateTime(new Date()));
		ent.setCreatedBy(XSPUtil.getCurrentSession().getEffectiveUserName());
		return ent;
	}
}
