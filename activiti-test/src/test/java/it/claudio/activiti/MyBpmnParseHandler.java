package it.claudio.activiti;

import java.util.Collection;

import org.activiti.bpmn.model.BaseElement;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.parse.BpmnParseHandler;

public class MyBpmnParseHandler implements BpmnParseHandler {

	@Override
	public Collection<Class<? extends BaseElement>> getHandledTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void parse(BpmnParse bpmnParse, BaseElement element) {
		// TODO Auto-generated method stub

	}

}
