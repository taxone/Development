package it.claudio;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.WebApplicationInitializer;

public class ApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
//		ClassPathXmlApplicationContext context = 
//				new ClassPathXmlApplicationContext("application-context.xml");
		
//		ServletRegistration facesServlet = servletContext.addServlet("FacesServlet", FacesServlet.class);
//		if(facesServlet==null){
//			facesServlet = servletContext.getServletRegistration("FacesServlet");
//		}
//		//facesServlet.setLoadOnStartup(1);
//		facesServlet.addMapping("*.xhtml");
		
	}

}
