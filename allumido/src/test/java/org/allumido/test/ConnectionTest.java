package org.allumido.test;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.exceptions.CmisConnectionException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisRuntimeException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionTest.class);
	
	@Test
	public void connect(){
		
		SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
		Map<String, String> parameter = new HashMap<String, String>();

		// User credentials.
		parameter.put(SessionParameter.USER, "admin");
		parameter.put(SessionParameter.PASSWORD, "admin");

		// Connection settings.
		parameter.put(SessionParameter.ATOMPUB_URL,
		    "http://localhost:8080/alfresco/service/cmis"); // URL to your CMIS server.
		// parameter.put(SessionParameter.REPOSITORY_ID, "myRepository"); // Only necessary if there is more than one repository.
		parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

		// Session locale.
		parameter.put(SessionParameter.LOCALE_ISO3166_COUNTRY, "");
		parameter.put(SessionParameter.LOCALE_ISO639_LANGUAGE, "en");
		parameter.put(SessionParameter.LOCALE_VARIANT, "US");

		// Create session.
		Session session = null;
		try {
		    // This supposes only one repository is available at the URL.
			List<Repository> repositories = sessionFactory.getRepositories(parameter);
			printRepositoryInfo(repositories);
			
		    Repository repository =
		        sessionFactory.getRepositories(parameter).get(0);
		    session = repository.createSession();
		    
		    Folder rootFolder = session.getRootFolder();
		}
		catch(CmisConnectionException e) { 
			e.printStackTrace();
		    
		}
		catch(CmisRuntimeException e) {
		    // The user/password have probably been rejected by the server.
			e.printStackTrace();
		}
		
		assertNotNull(session);
	}

	private void printRepositoryInfo(List<Repository> repositories) {
		for (Repository repository : repositories) {
			LOGGER.info("--- REPOSITORY INFO ------");
			String repoInfo = String.format("Name: %s - Description: %s - ProductName: %s ",
					repository.getName(), repository.getDescription(),repository.getProductName());
			LOGGER.info(repoInfo);
		}
	}
}
