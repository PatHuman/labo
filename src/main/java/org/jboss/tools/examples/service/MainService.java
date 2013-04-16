package org.jboss.tools.examples.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.tools.examples.model.Department;
import org.jboss.tools.examples.model.Member;
import org.jboss.tools.examples.model.Project;
import org.jboss.tools.examples.model.Task;
import org.jboss.tools.examples.model.Team;

/*
 * Then MainService handles the insertion of data
 * 
 * */

@Stateless
public class MainService {

	 @Inject
	   private Logger log;

	   @Inject
	   private EntityManager em;

	   @Inject
	   private Event<Member> memberEventSrc;
	   
	   @Inject
	   private Event<Department> departmentEventSrc;
	   
	   @Inject
	   private Event<Team> teamEventSrc;
	   
	   @Inject
	   private Event<Project> projectEventSrc;
	   
	   @Inject
	   private Event<Task> taskEventSrc;
	   
	   


	   
	   public void registerTask(Task task) throws Exception {
		      log.info("Registering " + task.getName());
		      em.persist(task);
		      taskEventSrc.fire(task);
		   }
	   
	   public void registerProject(Project p) throws Exception {
		      log.info("Registering " + p.getName());
		      em.persist(p);
		      projectEventSrc.fire(p);
		   }
	   
	   
	   public void registerTeam(Team team) throws Exception {
		      log.info("Registering " + team.getName());
		      em.persist(team);
		      teamEventSrc.fire(team);
		   }
	
	   public void registerDepartment(Department dpt) throws Exception {
		      log.info("Registering " + dpt.getName());
		      em.persist(dpt);
		      departmentEventSrc.fire(dpt);
		   }
	   
	   public void registerMember(Member member) throws Exception {
		      log.info("Registering " + member.getName());
		      em.persist(member);
		      memberEventSrc.fire(member);
		   }
}
