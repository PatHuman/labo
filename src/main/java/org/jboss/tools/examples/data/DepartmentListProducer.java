package org.jboss.tools.examples.data;

import org.jboss.tools.examples.model.Department;
 


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
public class DepartmentListProducer {
	
	   @Inject
	   private EntityManager em;

	   private List<Department> departments;
	   

	   // @Named provides access the return value via the EL variable name "members" in the UI (e.g.,
	   // Facelets or JSP view)
	   @Produces
	   @Named
	   public List<Department> getDepartments() {
	      return departments;
	   }
	   
   	   public void onDepartmentListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Department department) {
   		   retrieveAllDepartmentsOrderedByName();
	   }
   	   
   	   @PostConstruct
	   public void retrieveAllDepartmentsOrderedByName() {
	      CriteriaBuilder cb = em.getCriteriaBuilder();
	      CriteriaQuery<Department> criteria = cb.createQuery(Department.class);
	      Root<Department> department = criteria.from(Department.class);
	      
	      criteria.select(department).orderBy(cb.asc(department.get("name")));
	      departments = em.createQuery(criteria).getResultList();
	   }
	   	  
   	   
	  

	   

}
