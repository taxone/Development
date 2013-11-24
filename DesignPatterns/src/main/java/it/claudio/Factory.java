package it.claudio;

import it.claudio.domain.ConcreteBaseModel;

import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.reflections.Reflections;

public class Factory {

	private Map<String, Builder> map;
	
	public ConcreteBaseModel newInstance(String type){
		return null;
	}
	
	@PostConstruct
	public void init(){
//		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
//		
//		scanner.addIncludeFilter(new AnnotationTypeFilter(MyAnnotation.class));
//		
//		Set<BeanDefinition> components = scanner.findCandidateComponents("it.claudio.domain");
//		
//		for (BeanDefinition beanDefinition : components) {
//			System.out.println("Found annotated class: "+beanDefinition.getBeanClassName());
//		}
		
		Set<Class<?>> annotatedTypes = new Reflections("it.claudio.domain").getTypesAnnotatedWith(MyAnnotation.class);
		for (Class<?> clazz : annotatedTypes) {
			System.out.println("Found annotated class: "+clazz);
			
			MyAnnotation myAnnotation = clazz.getAnnotation(MyAnnotation.class);
		}
	}
}
