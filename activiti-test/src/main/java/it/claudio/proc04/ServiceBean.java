package it.claudio.proc04;

import lombok.Getter;
import lombok.Setter;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.el.Expression;

public class ServiceBean {

	public static final String A_VALUE = "A_VALUE";
	
	public static final String A_VAR = "it.claudio.proc04.ServiceBean.A_VAR";
	
	//TODO da rimuovere
	@Setter
	@Getter
	private Expression myField;

	//TODO da rimuovere
	public String execute(){
		return A_VALUE;
	}
	
	public void saveExecutionVariable(DelegateExecution execution){
		execution.setVariable(A_VAR, A_VALUE);
	}
	
	//TODO da rimuovere
	public String evaluateField(DelegateExecution execution){
		return (String) myField.getValue(execution);
	}
}
