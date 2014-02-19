package org.allumido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.FileableCmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;

@RequiredArgsConstructor
public class Node {

	@Getter
	private final CmisObject object;
	
	public List<Node> getChildren(){
		if(object instanceof Folder){
			ItemIterable<CmisObject> children = ((Folder)object).getChildren();
			List<Node> nodes = new ArrayList<>();
			
			for(CmisObject obj: children){
				nodes.add(new Node(obj));
			}
			
			return nodes;
		} else {
			return Collections.emptyList();
		}
	}
	
	public Node getPrimaryParent(){
		Node parent = null;
		
		if(object instanceof FileableCmisObject){
			parent = new Node(((FileableCmisObject)object).getParents().get(0));
		}
		
		return parent;
	}
}
