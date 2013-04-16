package org.jboss.tools.examples.controller;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.tools.examples.model.Department;
import org.jboss.tools.examples.service.MainService;


@Model
public class DepartmentController {
	
	
	@Inject
	   private FacesContext facesContext;

	   @Inject
	   private MainService departmentRegistration;
	   
	   private Department newDepartment;

	   @Produces
	   @Named
	   public Department getNewDepartment() {
	      return newDepartment;
	   }

	   public void register() throws Exception {
		   departmentRegistration.registerDepartment(newDepartment);
	      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
	      initNewDepartment();
	   }

	   @PostConstruct
	   public void initNewDepartment() {
	      newDepartment = new Department();
	   }

}
