package it.tasso;

import java.util.Date;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;

@ManagedBean
@ViewScoped
public class LocaleBean {

	@Getter
	private Locale locale;
	
	@Getter
	private Date date;

	@PostConstruct
	public void setUp(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		//Application application = facesContext.getApplication();
		
		locale = facesContext.getViewRoot().getLocale();
		
		
		date = new Date();
	}
}
