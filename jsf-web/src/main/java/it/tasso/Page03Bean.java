package it.tasso;

import javax.faces.bean.ManagedBean;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

@ManagedBean
public class Page03Bean implements ActionListener{

	public String execute(){
		return "page02";
	}

	@Override
	public void processAction(ActionEvent event)
			throws AbortProcessingException {
		System.out.println("ciao da action listener");
		
	}
}
