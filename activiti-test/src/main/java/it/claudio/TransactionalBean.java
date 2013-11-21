package it.claudio;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class TransactionalBean implements ITransactionalBean{

	public static final String ROLLBACK = "Rollback";
	@Autowired
	private RuntimeService runtimeService;
	
	@Transactional
	public void execute(){
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("MyProcess01");
		
		throw new RuntimeException(ROLLBACK);
	}
}
