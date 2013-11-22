package it.claudio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import it.claudio.proc04.ServiceBean;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.persistence.entity.HistoricScopeInstanceEntity;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ExecutionQuery;
import org.activiti.engine.runtime.NativeExecutionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:simple-context.xml")
public class SimpleTest {

	private static final String USERNAME = "CLAUDIO";
	@Autowired
	@Rule
	public ActivitiRule activitiSpringRule;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private HistoryService historyService;

	/**
	 * Prova di utilizzo di SQL nativo con il metodo "parameter"
	 * e relativa sintassi ("#{nome_parametro} nello statement SQL)
	 */
	@Test
	@Deployment(resources={"processes/MyProcess.bpmn","processes/SubProcess.bpmn"})
	public void test() {
		FlowContext flowContext = new FlowContext();
		activitiSpringRule.getIdentityService().setAuthenticatedUserId(USERNAME);
		
		RuntimeService runtimeService = activitiSpringRule.getRuntimeService();
		
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess");
		
		List<Execution> result = activitiSpringRule.getRuntimeService().createNativeExecutionQuery().sql(
				"select ru_ex.* from act_ru_execution ru_ex " +
				"inner join act_hi_actinst as a_hi on (a_hi.execution_id_ = ru_ex.id_) " +
				"where " +
//				"ru_ex.proc_inst_id_ = #{processInstanceId} " +
//				"and " +
				"a_hi.act_type_ = #{activityType}")
//				.parameter("processInstanceId", processInstance.getId())
				.parameter("activityType", "receiveTask").list();
		
		
		int size = result.size();
		
	}
	
	/**
	 * Il bean di Spring utilizzato come ServiceTask è referenziato come semplice 
	 * expressione (non delegate):
	 * 
	 * #{ServiceBean.execute()}
	 * 
	 * e viene definito il metodo di tale bean che va chiamato, senza che il Bean
	 * debba implementare alcuna interfaccia.
	 * 
	 */
	@Test
	@Deployment(resources={"processes/MyProcess04.bpmn"})
	public void springExpression(){
		String executionId = runtimeService.startProcessInstanceByKey("MyProcess04").getId();
		
		String variable = (String) runtimeService.getVariable(executionId, "result");
		
		assertEquals(ServiceBean.A_VALUE,variable);
	}
	
	/**
	 * Il metodo getActiveActivityIds(), che prende come parametro una processInstanceId, restituisce in effetti tutte 
	 * le attività in cui è fermo il processo. 
	 * 
	 */
	@Test
	@Deployment(resources={"processes/MyProcess05.bpmn","processes/MyProcess04.bpmn"})
	public void getActiveActivityIds(){
		String processInstanceId = runtimeService.startProcessInstanceByKey("MyProcess05").getProcessInstanceId();
		//1) Recupero tutte le esecuzioni attive con la variabile USER_ID uguale a quella dell'utente
		//2) Per ogni esecuzione, verifico che il tipo di attività sia "receiveTask" (sulla complete dell'attività potrei rimuovere la variabile, in modo da far 
		//sì che la query torni meno righe)
		//3) 
		
		
		List<String> activityIds = runtimeService.getActiveActivityIds(processInstanceId);
		List<String> activityTypes = new ArrayList<>();
		
		for (String activityId : activityIds) {
			HistoricActivityInstance activityInstance = historyService.createHistoricActivityInstanceQuery().activityId(activityId).unfinished().singleResult();
			activityTypes.add(activityInstance.getActivityType());
		}
		
		
		assertEquals("Il flusso ha generato 3 esecuzioni attive",3,activityIds.size());
		assertEquals(3,activityTypes.size());
	}
	
	/**
	 * Settare una variabile su una Execution, di fatto è come settarla globalmente: infatti,
	 * se setto una variable su  mainExecutionId allora essa è vista anche da subExecutionId.
	 * 
	 * Le variabili locali ad una esecuzione sono invece visibili solamente all'esecuzione
	 * a cui sono locali e nessun'altra.
	 * 
	 * Le variabili locali di una esecuzione sono accedibili anche come variabili globali se chiamate sull'esecuzione stessa,
	 * ovvero una variabile locale ad una certa esecuzione viene recuperata sia chiamando getVariableLocal() che getVariable()
	 * su quella esecuzione.
	 */
	@Test
	@Deployment(resources={"processes/MyProcess05.bpmn","processes/MyProcess04.bpmn"})
	public void executions(){
		final String variableName = "variableName";	
		final String localVariableName = "localVariableName";	
		final String localVariableName2 = "localVariableName2";
		final String value = "value";
		final String localValue = "localValue";
		
		String mainExecutionId = runtimeService.startProcessInstanceByKey("MyProcess05").getId();
		
		String activityId = runtimeService.getActiveActivityIds(mainExecutionId).get(0);
		
		String subExecutionId = runtimeService.createExecutionQuery().activityId(activityId).singleResult().getId();
		runtimeService.setVariable(mainExecutionId, variableName, value);
		runtimeService.setVariableLocal(mainExecutionId, localVariableName, localValue);
		runtimeService.setVariableLocal(subExecutionId, localVariableName2, localValue);
		
		assertFalse("Le 2 esecuzioni sono effettivamente diverse.",mainExecutionId.equals(subExecutionId));
		assertEquals("Le 2 esecuzioni vedono la stessa variabile",value,runtimeService.getVariable(subExecutionId, variableName));
		assertNull("La variabile locale di una esecuzione non è visibile ad un'altra esecuzione",runtimeService.getVariableLocal(subExecutionId, localVariableName));
		assertNull("La variabile locale di una esecuzione non è visibile ad un'altra esecuzione",runtimeService.getVariableLocal(mainExecutionId, localVariableName2));
		assertEquals("Le variabili locali di una esecuzione sono accedibili anche come variabili globali se chiamate sull'esecuzione stessa"
				,localValue,runtimeService.getVariable(mainExecutionId, localVariableName));
	}
	
	
}
