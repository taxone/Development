package it.tasso;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import lombok.Getter;
import lombok.Setter;


@ManagedBean
@ViewScoped
public class MyBean {

	
	
	@Getter
	@Setter
	private String value;
	
	@PostConstruct
	public void init(){
		value = "Inizializzato";
	}
	
	public String submit(){
		FacesContext facesCtx = FacesContext.getCurrentInstance();
		Flash flash = facesCtx.getExternalContext().getFlash();
		flash.put("myParam", "Pippo");
		
		facesCtx.getAttributes().put("fcParam", "Pluto");
		
		ExpressionFactory ef = facesCtx.getApplication().getExpressionFactory();
		
		ValueExpression valueExpression = ef.createValueExpression(facesCtx.getELContext(), "#{secondBean}", SecondBean.class);
		
		SecondBean secondBean = (SecondBean) valueExpression.getValue(facesCtx.getELContext());
		
		return "prime02?faces-redirect=true";
	}
}
