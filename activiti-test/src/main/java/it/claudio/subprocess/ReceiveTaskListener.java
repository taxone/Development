package it.claudio.subprocess;

import it.claudio.FlowContext;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

public class ReceiveTaskListener implements ExecutionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		FlowContext flowContext = FlowContext.getCurrentInstance();
		
		
		flowContext.storeToExecution(execution);

	}

}
