package it.tasso;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.PostConstructApplicationEvent;
import javax.faces.event.ListenerFor;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;


public class MySystemEventListener implements SystemEventListener {

	@Override
	public void processEvent(SystemEvent event) throws AbortProcessingException {
		PostConstructApplicationEvent anEvent = (PostConstructApplicationEvent) event;

		System.out.println("Got event "+anEvent);
	}

	@Override
	public boolean isListenerForSource(Object source) {
		return true;
	}

}
