package it.claudio.p02;

import java.lang.reflect.Proxy;

import it.claudio.bean.DepBean;
import it.claudio.bean.IBeanOne;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Main {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = 
				new GenericXmlApplicationContext(new ClassPathResource("application-context.xml"));
		
		
		//DepBean depBean = (DepBean) context.getBean("DepBean");
		
		IBeanOne beanOne = (IBeanOne) context.getBean("myProxyFactoryBean");
		
		beanOne.foo();
		
		return;
	}
}
