package it.claudio;

import it.claudio.domain.ConcreteBaseModel;
import it.claudio.domain.ModelOne;
import it.claudio.domain.ModelTwo;
import it.claudio.vo.Transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Main {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext(
				new ClassPathResource("application-context.xml"));
		
		Factory factory = context.getBean(Factory.class);
		
		List<ConcreteBaseModel> models = new ArrayList<>();
		
		ModelOne modelOne = new ModelOne("uno");
		ModelTwo modelTwo = new ModelTwo("due");
		
		models.add(modelOne);
		models.add(modelTwo);
		
		Transformer transformer = new Transformer();
		transformer.transform(models);
		
		List<Object> results = transformer.getResults();
		
		System.out.println("Results: ");
		for (Object object : results) {
			System.out.println(object);
		}
	}
}
