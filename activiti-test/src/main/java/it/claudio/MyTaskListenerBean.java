package it.claudio;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;

public class MyTaskListenerBean implements TaskListener {

	@Autowired
	private IProcessScopeBean processScopeBean;

	
	public void notify(DelegateTask delegateTask) {
		System.out.println("Ciao da Task Listener in fase di "+delegateTask.getEventName());
		//dSystem.out.println("Scoped Bean: "+processScopeBean.getValue());
	}

}
