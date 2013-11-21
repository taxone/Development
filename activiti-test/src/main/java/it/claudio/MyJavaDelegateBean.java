package it.claudio;

import lombok.Setter;
import lombok.experimental.Builder;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.el.Expression;
import org.springframework.beans.factory.annotation.Autowired;

public class MyJavaDelegateBean implements JavaDelegate {

	
	
	@Autowired
	private MyServiceBean myServiceBean;
	
	@Setter
	private Expression exprField;
	
	public void execute(DelegateExecution arg0) throws Exception {
		System.out.println("Ciao dal Java Delegate: "+exprField.getValue(arg0));

	}

}
