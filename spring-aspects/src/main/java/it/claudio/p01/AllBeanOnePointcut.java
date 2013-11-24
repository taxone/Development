package it.claudio.p01;

import it.claudio.bean.IBeanOne;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.RootClassFilter;

public class AllBeanOnePointcut implements Pointcut {

	@Override
	public ClassFilter getClassFilter() {
		return new RootClassFilter(IBeanOne.class);
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		return MethodMatcher.TRUE;
	}

}
