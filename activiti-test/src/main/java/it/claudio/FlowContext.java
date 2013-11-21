package it.claudio;

import java.util.UUID;

import lombok.Getter;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.identity.Authentication;

public class FlowContext {

	@Getter
	private final String authenticatedUserId;
	
	public static String EXECUTION_IDENTIFIER_VAR_NAME = "UUID";
	
	public static String USER_ID_VAR_NAME = "USER_ID";
	
	public FlowContext(){
		
		executionIdentifier = UUID.randomUUID();
		
		authenticatedUserId = Authentication.getAuthenticatedUserId();	
		
		setCurrentInstance(this);
	}
	
	@Getter
	private final UUID executionIdentifier;
	
	private static ThreadLocal<FlowContext> instance = new ThreadLocal<>();
	
	protected void setCurrentInstance(FlowContext ctx){
		instance.set(ctx);
	}
	
	public static FlowContext getCurrentInstance(){
		return instance.get();
	}
	
	public void storeToExecution(DelegateExecution delegateExecution){
		delegateExecution.setVariable(FlowContext.USER_ID_VAR_NAME,authenticatedUserId);
		delegateExecution.setVariable(EXECUTION_IDENTIFIER_VAR_NAME, executionIdentifier);
	}
}
