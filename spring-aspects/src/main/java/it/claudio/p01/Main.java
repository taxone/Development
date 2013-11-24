package it.claudio.p01;

import it.claudio.bean.BeanOne;
import it.claudio.bean.BeanTwo;
import it.claudio.bean.IBeanOne;
import it.claudio.bean.IBeanTwo;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class Main {

	public static void main(String[] args) {
		Advisor advisor = 
				new DefaultPointcutAdvisor(new AllBeanOnePointcut(), new MyMethodsInterceptor());

		
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(new BeanOne());
		pf.addAdvisor(advisor );
		IBeanOne bean = (IBeanOne) pf.getProxy();
				
		pf = new ProxyFactory();
		pf.setTarget(new BeanTwo());
		pf.addAdvisor(advisor );
		IBeanTwo beanTwo = (IBeanTwo) pf.getProxy();
		
		bean.foo();
		beanTwo.foo();
	}
}
