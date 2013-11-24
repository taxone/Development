package it.tasso;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;

@ViewScoped
@ManagedBean
public class SecondBean {

	@Getter
	@Setter
	@ManagedProperty(value="#{flash.myParam}")
	private String pippo;
	
	@Getter
	@Setter
	//@ManagedProperty(value="#{facesContext.attributes}")
	private String pluto;
	
	@PostConstruct
	public void init(){
		pluto = (String) FacesContext.getCurrentInstance().getAttributes().get("fcParam");
	}
}
