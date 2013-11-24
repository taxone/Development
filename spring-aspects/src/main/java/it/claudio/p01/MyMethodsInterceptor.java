package it.claudio.p01;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyMethodsInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		System.out.println("Advised "+methodInvocation.getMethod().getName());
		
		return methodInvocation.proceed();
	}

}
