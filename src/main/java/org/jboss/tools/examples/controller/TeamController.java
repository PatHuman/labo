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

import org.jboss.tools.examples.model.Team;
import org.jboss.tools.examples.service.MainService;

@Model
public class TeamController {
	
	   @Inject
	   private FacesContext facesContext;

	   @Inject
	   private MainService teamRegistration;
	   
	   private Team newTeam;

	   @Produces
	   @Named
	   public Team getNewTeam() {
	      return newTeam;
	   }

	   public void register() throws Exception {
		   teamRegistration.registerTeam(newTeam);
	      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
	      initNewTeam();
	   }

	   @PostConstruct
	   public void initNewTeam() {
	      newTeam = new Team();
	   }


}
