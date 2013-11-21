package it.claudio;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class ProcessScopeBean implements Serializable,IProcessScopeBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private String value;
}
