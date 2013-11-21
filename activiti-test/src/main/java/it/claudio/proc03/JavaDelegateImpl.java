package it.claudio.proc03;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class JavaDelegateImpl implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Object loopCounter = execution.getVariableLocal("loopCounter");
		execution.setVariableLocal("locVar", loopCounter);

		System.out.println("Loop Counter (execution local variable): "+loopCounter);
	}

}
