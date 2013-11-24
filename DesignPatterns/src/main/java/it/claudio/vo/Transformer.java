package it.claudio.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import it.claudio.domain.ConcreteBaseModel;
import it.claudio.domain.IProcessor;
import it.claudio.domain.ModelOne;
import it.claudio.domain.ModelTwo;

public class Transformer implements IProcessor {

	@Getter
	private List<Object> results = new ArrayList<>();
	
	@Override
	public void process(ModelOne modelOne) {
		System.out.println("Processing ModelOne: "+modelOne.toString());

		ModelOneVO modelOneVO = new ModelOneVO(modelOne.getValue());
		results.add(modelOneVO);
	}

	@Override
	public void process(ModelTwo modelTwo) {
		System.out.println("Processing ModelTwo: "+modelTwo.toString());
		
	}

	public void transform(List<ConcreteBaseModel> models){
		for (ConcreteBaseModel concreteBaseModel : models) {
			concreteBaseModel.accept(this);
		}
	}
}
