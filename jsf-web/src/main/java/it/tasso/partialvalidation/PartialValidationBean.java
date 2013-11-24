package it.tasso.partialvalidation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIData;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.sun.faces.context.AjaxExceptionHandlerImpl;



@RequestScoped
@ManagedBean(name="partialValidation")
public class PartialValidationBean {

	
	public static class DataBean {
		
		public DataBean(String value) {
			super();
			this.value = value;
		}

		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	
	@PostConstruct
	public void init(){
		values.add(new DataBean("ciao"));
	}
	

	private List<DataBean> values = new ArrayList<>();
	
	
	public List<DataBean> getValues() {
		return values;
	}

	public void setValues(List<DataBean> values) {
		this.values = values;
	}

	public String getValueOne() {
		return valueOne;
	}

	public void setValueOne(String valueOne) {
		this.valueOne = valueOne;
	}

	public String getValueTwo() {
		return valueTwo;
	}

	public void setValueTwo(String valueTwo) {
		this.valueTwo = valueTwo;
	}

	public UIData getUiData() {
		return uiData;
	}

	public void setUiData(UIData uiData) {
		this.uiData = uiData;
	}

	public UIInput getUiInput() {
		return uiInput;
	}

	public void setUiInput(UIInput uiInput) {
		this.uiInput = uiInput;
	}


	private String valueOne;
	
	
	private String valueTwo;
	
	
	private UIData uiData;
	
	
	private UIInput uiInput;
	
	public String action(){
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Azione Eseguita"));
		
		return null;
	}
	
	public void listener(AjaxBehaviorEvent event){
		valueOne = valueTwo;
	}
	
	
	public void action2(){
//		uiData.setRowIndex(0);
//		uiInput.setSubmittedValue(null);
//		uiInput.setValid(true);
		
		values.get(0).setValue("mondo");
		
		valueOne = "valore uno";
	}
}
