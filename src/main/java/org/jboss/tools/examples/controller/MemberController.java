package org.jboss.tools.examples.controller;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.tools.examples.model.Member;
import org.jboss.tools.examples.service.MainService;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class MemberController {

   @Inject
   private FacesContext facesContext;

   @Inject
   private MainService memberRegistration;

   private Member newMember;

   @Produces
   @Named
   public Member getNewMember() {
      return newMember;
   }

   public void register() throws Exception {
      memberRegistration.registerMember(newMember);
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
      initNewMember();
   }

   @PostConstruct
   public void initNewMember() {
      newMember = new Member();
   }
}