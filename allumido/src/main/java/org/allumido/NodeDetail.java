package org.allumido;

import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Session;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NodeDetail {

	private final Node node;
	
	private final Session session;
	
	public ObjectType getObjectType(){
		ObjectType typeDefinition = session.getTypeDefinition(node.getObject().getType().getId());
		
		return typeDefinition;
	}
}
