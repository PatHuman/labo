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

import org.jboss.tools.examples.model.Department;


/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the members table.
 */
@Path("/departments")
@RequestScoped
public class DepartmentRestService {
	
	   @Inject
	   private EntityManager em;

	   @GET
	   @Produces(MediaType.APPLICATION_JSON)
	   public List<Department> departmentList() {
	      // Use @SupressWarnings to force IDE to ignore warnings about "genericizing" the results of
	      // this query
	      @SuppressWarnings("unchecked")
	      // We recommend centralizing inline queries such as this one into @NamedQuery annotations on
	      // the @Entity class
	      // as described in the named query blueprint:
	      // https://blueprints.dev.java.net/bpcatalog/ee5/persistence/namedquery.html
	      final List<Department> results = em.createQuery("select m from Department m order by m.name").getResultList();
	      return results;
	   }

	   @GET
	   @Path("/{id:[0-9][0-9]*}")
	   @Produces(MediaType.APPLICATION_JSON)
	   public Department lookupDepartmentById(@PathParam("id") long id) {
	      return em.find(Department.class, id);
	   }

}
