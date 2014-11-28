package com.olb;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

public class CustomValidators implements Serializable {

	private static final long serialVersionUID = -367636424615606944L;
	
	public CustomValidators(){
		
	}
	
	public void validateEmail(FacesContext context, UIComponent component, Object value){
		if(!value.toString().matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")){
			FacesMessage message = new FacesMessage("Invalid E-Mail adress");
//			context.addMessage(component.getClientId(context), message);
			throw new ValidatorException(message);
		}
	}
	
}
