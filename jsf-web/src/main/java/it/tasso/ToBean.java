package it.tasso;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.Getter;
import lombok.Setter;

@ViewScoped
@ManagedBean
public class ToBean {

	@Getter
	@Setter
	private String param;
	
	@Getter
	@Setter
	private MyData myData;
}
