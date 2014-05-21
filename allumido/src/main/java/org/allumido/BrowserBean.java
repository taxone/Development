package org.allumido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import lombok.Getter;

import org.apache.chemistry.opencmis.client.api.CmisObject;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.exceptions.CmisConnectionException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisRuntimeException;

@ManagedBean
@SessionScoped
public class BrowserBean {

	private static final String ALFRESCO_LOCALHOST = "http://localhost:8080/alfresco/service/cmis";
	
	private static final String ALFRESCO_REMOTE = "http://cmis.alfresco.com/cmisatom";

	private Session session;
	
	@Getter
	private Node currentObject;

	@Getter
	private ListDataModel<Node> folderDataModel;

	@Getter
	private NodeDetail nodeDetail;
	
	@PostConstruct
	public void init(){
		
		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		Map<String, String> parameter = new HashMap<String, String>();

		// User credentials.
		parameter.put(SessionParameter.USER, "admin");
		parameter.put(SessionParameter.PASSWORD, "cpr2000");

		// Connection settings.
		parameter.put(SessionParameter.ATOMPUB_URL,
				ALFRESCO_LOCALHOST); // URL to your CMIS server.
		// parameter.put(SessionParameter.REPOSITORY_ID, "myRepository"); // Only necessary if there is more than one repository.
		parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

		// Session locale.
		parameter.put(SessionParameter.LOCALE_ISO3166_COUNTRY, "");
		parameter.put(SessionParameter.LOCALE_ISO639_LANGUAGE, "en");
		parameter.put(SessionParameter.LOCALE_VARIANT, "US");

		parameter.put(SessionParameter.OBJECT_FACTORY_CLASS, "org.alfresco.cmis.client.impl.AlfrescoObjectFactoryImpl");

		
		// Create session.
		session = null;
		try {
		    // This supposes only one repository is available at the URL.
			List<Repository> repositories = sessionFactory.getRepositories(parameter);
			
			
		    Repository repository =
		        sessionFactory.getRepositories(parameter).get(0);
		    session = repository.createSession();
		    
		    Folder rootFolder = session.getRootFolder();
		    updateFoldersListDataModel(new Node(rootFolder));
		    
		}
		catch(CmisConnectionException e) { 
			e.printStackTrace();
		    
		}
		catch(CmisRuntimeException e) {
		    // The user/password have probably been rejected by the server.
			e.printStackTrace();
		}
	}
	

	
	private void updateFoldersListDataModel(Node n){
//		List<Node> children = n.getChildren();
//		List<Node> folders = new ArrayList<>();
//		for (CmisObject cmisObject : children) {
//			
//			if(cmisObject instanceof Folder){
//				Node folderModel = new Node((Folder) cmisObject);
//				folders.add(folderModel);
//			}
//			
//			
//		}
		
		folderDataModel = new ListDataModel<>(n.getChildren());
	}
	
	public void select(){
		current( folderDataModel.getRowData());
	}
	
	public void parent(){
				
		current( currentObject.getPrimaryParent());
		
	}
	
	private void current(Node n){
		currentObject = n;
		updateFoldersListDataModel( currentObject);
		nodeDetail = new NodeDetail(currentObject, session);
	}
}
