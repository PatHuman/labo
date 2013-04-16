package org.jboss.tools.examples.data;

 
import org.jboss.tools.examples.model.Project;
 


import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@RequestScoped
public class ProjectListProducer {
	
	   @Inject
	   private EntityManager em;

	   private List<Project> projects;
	   

	    
	   @Produces
	   @Named
	   public List<Project> getProjects() {
	      return projects;
	   }
	   
   	   public void onProjectListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Project project) {
   		   retrieveAllProjectsOrderedByName();
	   }
   	   
   	   @PostConstruct
	   public void retrieveAllProjectsOrderedByName() {
	      CriteriaBuilder cb = em.getCriteriaBuilder();
	      CriteriaQuery<Project> criteria = cb.createQuery(Project.class);
	      Root<Project> project = criteria.from(Project.class);
	       
	      criteria.select(project).orderBy(cb.asc(project.get("name")));
	      projects = em.createQuery(criteria).getResultList();
	   }
	   	  
   	   
	  

	   

}
