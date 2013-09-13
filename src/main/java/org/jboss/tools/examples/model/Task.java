package org.jboss.tools.examples.model;

import javax.persistence.Entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;

import java.lang.Override;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Task implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Column
   @NotEmpty(message = "Task name please")
   private String name;

   @Column
   @NotNull(message = "Please describe")
   @Size(message = "Must be <10 and <1000", min = 10, max = 1000)
   private String description;

   @Column
   @NotEmpty(message = "select a project please")
   private String project;

   @Column
   @NotEmpty(message = "select a user please")
   private String owner;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object that)
   {
      if (this == that)
      {
         return true;
      }
      if (that == null)
      {
         return false;
      }
      if (getClass() != that.getClass())
      {
         return false;
      }
      if (id != null)
      {
         return id.equals(((Task) that).id);
      }
      return super.equals(that);
   }

   @Override
   public int hashCode()
   {
      if (id != null)
      {
         return id.hashCode();
      }
      return super.hashCode();
   }

   public String getName()
   {
      return this.name;
   }

   public void setName(final String name)
   {
      this.name = name;
   }

   public String getDescription()
   {
      return this.description;
   }

   public void setDescription(final String description)
   {
      this.description = description;
   }

   public String getProject()
   {
      return this.project;
   }

   public void setProject(final String project)
   {
      this.project = project;
   }

   public String getOwner()
   {
      return this.owner;
   }

   public void setOwner(final String owner)
   {
      this.owner = owner;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (name != null && !name.trim().isEmpty())
         result += "name: " + name;
//      if (description != null && !description.trim().isEmpty())
//         result += ", description: " + description;
//      if (project != null && !project.trim().isEmpty())
//         result += ", project: " + project;
//      if (owner != null && !owner.trim().isEmpty())
//         result += ", owner: " + owner;
      return result;
   }
}