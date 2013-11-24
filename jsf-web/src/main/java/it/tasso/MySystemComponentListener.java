package it.tasso;

import javax.faces.bean.ManagedBean;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ComponentSystemEventListener;
import javax.faces.event.PreRenderViewEvent;

@ManagedBean(name="myListener")
public class MySystemComponentListener implements ComponentSystemEventListener{

	@Override
	public void processEvent(ComponentSystemEvent anEvent)			throws AbortProcessingException {
		
		PreRenderViewEvent event = (PreRenderViewEvent) anEvent;
		
		System.out.println("Event catched: "+event);
	}

}
