package it.claudio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import it.claudio.proc03.JavaDelegateImpl;

import java.util.List;

import javax.annotation.Resource;

import lombok.javac.ResolutionResetNeeded;

import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class MyTest {

	@Autowired
	@Rule
	public ActivitiRule activitiSpringRule;
	
	@Resource(name="MyTransactionalBean")
	private ITransactionalBean transactionalBean;

	/**
	 * Il bean di Spring #{MyJavaDelegateBean} viene utilizzato come JavaDelegate
	 * utilizzando il campo "Delegate Expression" del ServiceTask; le field expression
	 * funzionano solo se si usa la DelegateExpression, altrimenti non vengono passate.
	 * 
	 * Il bean di Spring #{MyTaskListener] è utilizzato come TaskListener
	 * usando "Delegate Expression" del listener di uno UserTask.
	 * 
	 * Esempio di {@link ActivityBehavior} in un service task (sempre come bean di spring).
	 * 
	 * Authenticated user risulta essere un "partecipante" del processo.
	 * 
	 * E' possibile definire ruoli custom per i task, come ad esempio lo "Executor", in modo da individuare
	 * ad esempio l'utente che effettivamente compie un determinato task.
	 * 
	 * Si possono utilizzare sia le annotazioni che la definizione Spring per deployare un processo.
	 * Quando si esegue un test, vengono deployate entrambe le risorse.
	 * 
	 */
	@Test
	@Deployment(resources={"MyProcess01.bpmn"})
	public void beanSpring() {
		RuntimeService runtimeService = activitiSpringRule.getRuntimeService();
		TaskService taskService = activitiSpringRule.getTaskService();
		ManagementService managementService = activitiSpringRule.getManagementService();
		RepositoryService repositoryService = activitiSpringRule.getRepositoryService();
		
		assertEquals("La risorsa annotata è stata correttamente deployata",1, repositoryService.createDeploymentQuery().deploymentName("MyTest.beanSpring").count());
		assertEquals("La risorsa definita nel file XML di Spring  è stata correttamente deployata",1,repositoryService.createDeploymentQuery().deploymentName("Deploy Comune").count());

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("MyProcess01");
		
		
		Task task = taskService.createTaskQuery().singleResult();
		
		activitiSpringRule.getIdentityService().setAuthenticatedUserId("claudio");
		String taskId = task.getId();
		taskService.addUserIdentityLink(taskId, "claudio","Executor");
		taskService.complete(taskId);
		
		
	
		List<HistoricIdentityLink> identityLinksForTask = activitiSpringRule.getHistoryService().getHistoricIdentityLinksForTask(taskId);
		List<HistoricIdentityLink> historicIdentityLinksForProcessInstance = activitiSpringRule.getHistoryService().getHistoricIdentityLinksForProcessInstance(processInstance.getProcessInstanceId());
		
		assertEquals("Executor",identityLinksForTask.get(0).getType());
		assertEquals(1,historicIdentityLinksForProcessInstance.size());
		HistoricIdentityLink identityLink = historicIdentityLinksForProcessInstance.get(0);
		System.out.println("Authenticated User "+identityLink.getUserId()+" was involved as "+identityLink.getType());
		
		assertEquals("Il bean di Spring #{MyJavaDelegateBean} ha correttamente letto la Field Expression.",
				1,activitiSpringRule.getHistoryService().createHistoricVariableInstanceQuery()
				.variableValueEquals(it.claudio.MyJavaDelegateBean.VAR_NAME, "ciao").count());
	}
	
	/**
	 * La transazione eseguita dal bean fa rollback a causa dell'eccezione.
	 * Quindi lo unit test vede che non ci sono processi.
	 * Se rimuovo l'attributo transazionale da {@link TransactionalBean#execute()} allora
	 * il dato risulterà salvato su DB.
	 */
	@Test
	@Deployment(resources={"MyProcess01.bpmn"})
	public void transactionTest(){
		RuntimeException t = null;
		
		try {
			transactionalBean.execute();
		} catch (RuntimeException e) {
			//OK
			t = e;
		}
		
		long count = activitiSpringRule.getRuntimeService().createProcessInstanceQuery().count();
		
		assertEquals(0,count);
		assertEquals(TransactionalBean.ROLLBACK,t.getMessage());
	}
	
	/**
	 * Nel {@link JavaDelegateImpl} recupero la variabile di sistema "loopCounter", che è
	 * una variable di esecuzione locale.
	 * Inoltre, verifico che le variabili di esecuzione locali sono salvate sul db.
	 */
	@Test
	@Deployment(resources = { "MyProcess03.bpmn" })
	public void localScopeExecution() {
		RuntimeService runtimeService = activitiSpringRule.getRuntimeService();
		TaskService taskService = activitiSpringRule.getTaskService();
		
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("MyProcess03");
		
		List<Task> tasks = taskService.createTaskQuery().list();
		
		for (Task task : tasks) {
			Object variableLocal = runtimeService.getVariableLocal(task.getExecutionId(), "locVar");
			System.out.println("Example local variable: "+variableLocal);
		}
	}
	
	/**
	 * Verifica che l'utente "starter" definito nello start event come "initiator" del processo
	 *  corrisponda a quello autenticato.
	 */
	@Test
	@Deployment(resources={"MyProcess03.bpmn" })
	public void starter() {
		activitiSpringRule.getIdentityService().setAuthenticatedUserId("claudio");
		
		RuntimeService runtimeService = activitiSpringRule.getRuntimeService();		
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("MyProcess03");
		
		assertEquals("claudio",runtimeService.getVariable(processInstance.getId(), "myStarter"));
	}
}
