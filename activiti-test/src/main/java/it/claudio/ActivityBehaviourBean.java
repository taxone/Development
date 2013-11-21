package it.claudio;

import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.springframework.beans.factory.annotation.Autowired;

public class ActivityBehaviourBean implements ActivityBehavior {

	@Autowired
	private IProcessScopeBean processScopeBean;
	
	public void execute(ActivityExecution execution) throws Exception {
		processScopeBean.setValue("Inizio "+execution.getProcessInstanceId());
		
		PvmActivity activity = execution.getActivity();

		PvmTransition transition = activity.findOutgoingTransition("flow4");
		System.out.println("Taking transition "+transition.getId());
		
		execution.take(transition);
		
		//Nota come questo codice viene eseguito dopo la chiamata al listener del task
		//successivo, questo significa che è sincrono
		System.out.println("After taken transition "+transition.getId());
	}

}
