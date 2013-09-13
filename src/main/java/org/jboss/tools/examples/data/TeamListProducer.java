package org.jboss.tools.examples.data;



import org.jboss.tools.examples.model.Department;
import org.jboss.tools.examples.model.Member;
import org.jboss.tools.examples.model.Team;




import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


@ManagedBean(name="team_")
@RequestScoped
public class TeamListProducer {
	
	   @Inject
	   private EntityManager em;

	    
	  
	   private List<Team> teams;
	  // private List<Tuple> teamList;
	   
 
	   @Produces
	   @Named
	   public List<Team> getTeams() {
	      return teams ;
	   }
	   
	 /*  @Produces
	   @Named
	   public List<Tuple> getList() {
	      return teamList ;
	   }*/

	   public void onTeamListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Team team) {
	      retrieveAllTeamsOrderedByName();
	       
	   }

	     
		  
		    
	   
	   @PostConstruct
	   public void retrieveAllTeamsOrderedByName() {
		   
		   
		   
	      CriteriaBuilder cb = em.getCriteriaBuilder();
	      CriteriaQuery<Team> criteria = cb.createQuery(Team.class);
	      Root<Team> team = criteria.from(Team.class);
	    
	      
	      criteria.select(team).orderBy(cb.asc(team.get("name")));
	      teams = em.createQuery(criteria).getResultList();
	      
	     
	  
	   }
	   
	      
 
	  /* @PostConstruct
	   public void retrieveAllTeamsOrderedByName() {
		      
		      
		      CriteriaBuilder cb = em.getCriteriaBuilder();
		      CriteriaQuery<Tuple> cq = cb.createTupleQuery();
		      Root<Team> team = cq.from(Team.class);
			  
				
				cq.multiselect(team.get("name"));
				teamList=  em.createQuery(cq).getResultList();
				 
		   }*/

}
