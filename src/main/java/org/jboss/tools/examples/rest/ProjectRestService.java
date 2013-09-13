package org.jboss.tools.examples.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.tools.examples.model.Project;
import org.jboss.tools.examples.model.Task;

@Path("/projects")
@RequestScoped
public class ProjectRestService {
	
	
	@Inject
	   private EntityManager em;

	   @GET
	   @Produces(MediaType.APPLICATION_JSON)
	   public List<Project> listAllProjects() {
	      // Use @SupressWarnings to force IDE to ignore warnings about "genericizing" the results of
	      // this query
	      @SuppressWarnings("unchecked")
	      // We recommend centralizing inline queries such as this one into @NamedQuery annotations on
	      // the @Entity class
	      // as described in the named query blueprint:
	      // https://blueprints.dev.java.net/bpcatalog/ee5/persistence/namedquery.html
	      final List<Project> results = em.createQuery("select m from Project m order by m.name").getResultList();
	      return results;
	   }

	   @GET
	   @Path("/{id:[0-9][0-9]*}")
	   @Produces(MediaType.APPLICATION_JSON)
	   public Project lookupProjectById(@PathParam("id") long id) {
		   
		     return em.find(Project.class, id);
	   }
	   
	   @GET
	   @Path("/task/{id:[0-9][0-9]*}")
	   @Produces(MediaType.APPLICATION_JSON)
	   public Task lookupTaskById(@PathParam("id") long id) {
	      return em.find(Task.class, id);
	   }
	   
	   @GET
	   @Path("/tasks")
	   @Produces(MediaType.APPLICATION_JSON)
	   public List<Task> listAllTasks() {
	      
	      @SuppressWarnings("unchecked")
 
	      final List<Task> results = em.createQuery("select m from Task m order by m.name").getResultList();
	      return results;
	   }


}
