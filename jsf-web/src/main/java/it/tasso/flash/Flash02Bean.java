package it.tasso.flash;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ActionEvent;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
public class Flash02Bean {

	@Setter
	@Getter
	@ManagedProperty("#{flash.VAL1}")
	private String value;
	
	public void putValueInFlash(ActionEvent event){
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		
		flash.put("VAL2", "ciao VAL2");
	}
}
