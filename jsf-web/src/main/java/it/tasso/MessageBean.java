package it.tasso;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class MessageBean {

	public void addMessages(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("global"));
		
		FacesContext.getCurrentInstance().addMessage("miaform:placeholder", new FacesMessage("specific"));
		
	}
}
