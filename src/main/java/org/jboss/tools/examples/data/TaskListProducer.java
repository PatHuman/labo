
package org.jboss.tools.examples.data;

import org.jboss.tools.examples.model.Task;
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
public class TaskListProducer {
   @Inject
   private EntityManager em;

   private List<Task> tasks;

   // @Named provides access the return value via the EL variable name "members" in the UI (e.g.,
   // Facelets or JSP view)
   @Produces
   @Named
   public List<Task> getTasks() {
      return tasks;
   }

   public void onTaskListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Task task) {
      retrieveAllTasksOrderedByName();
   }

   @PostConstruct
   public void retrieveAllTasksOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Task> criteria = cb.createQuery(Task.class);
      Root<Task> task = criteria.from(Task.class);
       
      criteria.select(task).orderBy(cb.asc(task.get("name")));
      tasks = em.createQuery(criteria).getResultList();
   }
   
   

}
