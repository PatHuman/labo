package org.jboss.tools.examples.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.tools.examples.model.Team;

@Path("/teams")
@RequestScoped
public class TeamRestService {
	
	   @Inject
	   private EntityManager em;
	   
	   private List<Tuple> res;

	   @GET
	   @Produces(MediaType.APPLICATION_JSON)
	   public List<Team> listAllTeams() {
	      
	      @SuppressWarnings("unchecked")
	      final List<Team> results = em.createQuery("select m from Team m order by m.name").getResultList();
	      return results;
	   }

	   @GET
	   @Path("/{id:[0-9][0-9]*}")
	   @Produces(MediaType.APPLICATION_JSON)
	   public Team lookupTeamById(@PathParam("id") long id) {
	      return em.find(Team.class, id);
	   }
	   
	  

}
