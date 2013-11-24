package it.claudio.domain;

import it.claudio.MyAnnotation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@MyAnnotation("type1")
public class ModelOne extends ConcreteBaseModel {

	@Getter
	private final String value;
	
	@Override
	public void accept(IProcessor processor) {
		processor.process(this);
		
	}

}
