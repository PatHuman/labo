package org.jboss.tools.examples.controller;

import java.util.List;
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
import javax.ws.rs.GET;

import org.jboss.tools.examples.model.Member;
import org.jboss.tools.examples.model.Task;
import org.jboss.tools.examples.service.MainService;

@Model
public class TaskController {

	
	   @Inject
	   private FacesContext facesContext;

	   @Inject
	   private MainService taskRegistration;
	   
	   @Inject
	   private EntityManager em;
	   
	   private Task newTask;
	   


	   @Produces
	   @Named
	   public Task getNewTask() {
	      return newTask;
	   }

	   public void register() throws Exception {
		   taskRegistration.registerTask(newTask);
	      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
	      initNewTask();
	   }

	   @PostConstruct
	   public void initNewTask() {
	      newTask = new Task();
	   }
	   
	 
}
