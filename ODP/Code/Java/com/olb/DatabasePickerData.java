package com.olb;

import java.util.List;

import com.ibm.xsp.complex.ValueBindingObjectImpl;
import com.ibm.xsp.extlib.component.picker.data.INamePickerData;
import com.ibm.xsp.extlib.component.picker.data.IPickerEntry;
import com.ibm.xsp.extlib.component.picker.data.IPickerOptions;
import com.ibm.xsp.extlib.component.picker.data.IPickerResult;

public class DatabasePickerData extends ValueBindingObjectImpl implements INamePickerData {

	public String[] getSourceLabels() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasCapability(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<IPickerEntry> loadEntries(Object[] arg0, String[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public IPickerResult readEntries(IPickerOptions arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
