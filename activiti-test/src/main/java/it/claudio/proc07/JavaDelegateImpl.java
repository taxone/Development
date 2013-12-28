package it.claudio.proc07;

import it.claudio.proc04.ServiceBean;
import lombok.Getter;
import lombok.Setter;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.el.Expression;

public class JavaDelegateImpl implements JavaDelegate {

	@Setter
	@Getter
	private Expression myField;
	
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ServiceBean 	serviceBean = (ServiceBean) myField.getValue(execution);
		
		serviceBean.saveExecutionVariable(execution);
	}

}
