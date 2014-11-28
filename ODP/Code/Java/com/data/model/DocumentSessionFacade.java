package com.data.model;

public class DocumentSessionFacade {

	public DocumentEntity createDocument() {
		DocumentEntity doc = DocumentStorageService.getInstance().createObject();
		return doc;
	}

	public DocumentEntity createObject() {
		return DocumentStorageService.getInstance().createObject();
	}

	public void saveDocument(DocumentEntity doc) {
		try {
			DocumentStorageService.getInstance().save(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public DocumentEntity getDocumentById(String id) {
		return DocumentStorageService.getInstance().getById(id);
	}
}
