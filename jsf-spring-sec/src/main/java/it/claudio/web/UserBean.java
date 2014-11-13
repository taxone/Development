package it.claudio.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean
@RequestScoped
public class UserBean {

	public String getUserInfo(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String name = authentication.getName();
		
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		
		return sb.toString();
	}
}
