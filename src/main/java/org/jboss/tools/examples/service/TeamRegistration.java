package org.jboss.tools.examples.service;

import org.jboss.tools.examples.model.Member;
import org.jboss.tools.examples.model.Team;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

@Stateless
public class TeamRegistration {

	
	   @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Team> teamEventSrc;

	   public void register(Team team) throws Exception {
	      log.info("Registering " + team.getName());
	      em.persist(team);
	      em.flush();
	      teamEventSrc.fire(team);
	   }
	
}
