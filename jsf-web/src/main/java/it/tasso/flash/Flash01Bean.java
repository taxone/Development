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
public class Flash01Bean {

	@Setter
	@Getter
	@ManagedProperty("#{flash.VAL1}")
	private String value;
	
	@Setter
	@Getter
	@ManagedProperty("#{flash.VAL2}")
	private String value2;
	
	public void putValueInFlash(ActionEvent event){
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		
		flash.put("VAL1", "ciao");
	}
	
	@Getter
	@Setter
	private String testVal;
}
