package org.allumido;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.apache.chemistry.opencmis.client.api.Property;

@RequiredArgsConstructor
public class PropertyEditor<U> {

	@Getter
	private final Property<U> property;
	
	private U value;
	
	public U getValue(){
		return property.getValue();
	}
	
	public void setValue(U u){
		this.value = u;
	}
	
	public U getUpdValue(){
		return value;
	}
}
