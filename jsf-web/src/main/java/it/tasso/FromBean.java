package it.tasso;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
public class FromBean {

	@Getter
	@Setter
	private MyData myData;
	
	@PostConstruct
	public void init(){
		myData = new MyData();
		myData.setF1("ciao F1");
		myData.setF2(43l);
	}
}
