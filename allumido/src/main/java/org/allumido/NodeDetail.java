package org.allumido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import lombok.Getter;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Property;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.CmisExtensionElement;
import org.apache.chemistry.opencmis.commons.enums.ExtensionLevel;
import org.primefaces.event.RowEditEvent;


public class NodeDetail {

	public NodeDetail(Node node, Session session) {
		this.node = node;
		this.session = session;
		
		init();
	}

	private final Node node;
	
	private final Session session;

	@Getter
	private DataModel<PropertyEditor<?>> propertyDataModel;
	
	
	
	public ObjectType getObjectType(){
		ObjectType typeDefinition = session.getTypeDefinition(node.getObject().getType().getId());
		
		return typeDefinition;
	}
	
	
	
	public List<CmisExtensionElement> getExtensions(){
		List<CmisExtensionElement> extensions = node.getObject().getExtensions(ExtensionLevel.PROPERTIES);
		
		return extensions;
	}
	
	private void init(){
		List<Property<?>> properties = node.getObject().getProperties();
		List<PropertyEditor<?>> propertyEditors = new ArrayList<>();
		for (Property<?> property : properties) {
			propertyEditors.add(new PropertyEditor<>(property));
		}
		
		propertyDataModel = new ListDataModel<>(propertyEditors);
	}
	
	
	
	public void onEditProperty(RowEditEvent event){
		Map<String, Object> properties = new HashMap<String, Object>();
		//properties.put(propertyEditor.getProperty().getId(), propertyEditor.getValue());
		PropertyEditor pe = (PropertyEditor)event.getObject();
		Object value = pe.getUpdValue();
		
		//properties.put(PropertyIds.NAME,value);
		properties.put(pe.getProperty().getId(),value);
		
		 node.getObject().updateProperties(properties,true);
		 
		 init();
		
//		CmisObject cmisObject = node.getObject().updateProperties(properties);
//		
//		String name = cmisObject.getName();
//		Property<Object> property = cmisObject.getProperty(PropertyIds.NAME);
		
		return;
	}
}
