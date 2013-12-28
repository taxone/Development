package it.claudio;

import lombok.Setter;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

public class MyJavaDelegateBean implements JavaDelegate {

	public static final String VAR_NAME = MyJavaDelegateBean.class.getName()+".VAR_NAME";
	
	@Autowired
	private MyServiceBean myServiceBean;
	
	@Setter
	private Expression exprField;
	
	public void execute(DelegateExecution execution) throws Exception {
		Object value = exprField.getValue(execution);
		System.out.println("Ciao dal Java Delegate: "+value);

		execution.setVariable(VAR_NAME, value);
	}

}
