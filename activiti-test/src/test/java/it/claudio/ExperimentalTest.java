package it.claudio;

import static org.junit.Assert.assertNotNull;

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
@ContextConfiguration("/experimental-context.xml")
public class ExperimentalTest {

	@Autowired
	@Rule
	public ActivitiRule activitiSpringRule;
	
	/**
	 * La risorsa di deploy (cioè il processo) si trova nel file di contesto di Spring.
	 */
	@Test
	public void test() {
		ProcessInstance processInstance = activitiSpringRule.getRuntimeService().startProcessInstanceByKey("SimpleProcess");
		
		assertNotNull(processInstance);
	}
}
