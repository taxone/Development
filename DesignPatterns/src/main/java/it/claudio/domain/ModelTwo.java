package it.claudio.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class ModelTwo extends ConcreteBaseModel {

	@Getter
	private final String value;
	
	@Override
	public void accept(IProcessor processor) {
		
		processor.process(this);
	}

}
