package org.jboss.tools.examples.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.lang.Override;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Task implements Serializable
{

   @Id
   private @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   Long id = null;
   @Version
   private @Column(name = "version")
   int version = 1;

   @Column
   private String name;

   @Column
   private @Size(message = "[20|1000]", min = 20, max = 1000)
   String description;

   @Column
   private String project;

   @Column
   private String owner;
   
   @ManyToOne
   @JoinColumn(name="project_id")
   private Project project_id;

   public Project getProject_id() {
	return project_id;
}

public void setProject_id(Project project_id) {
	this.project_id = project_id;
}

public Member getOwner_id() {
	return owner_id;
}

public void setOwner_id(Member owner_id) {
	this.owner_id = owner_id;
}

@ManyToOne
   @JoinColumn(name="owner_id")
   private Member owner_id;
   
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

   public String toString()
   {
      String result = "";
      if (name != null && !name.trim().isEmpty())
         result += name;
      if (description != null && !description.trim().isEmpty())
         result += " " + description;
      if (project != null && !project.trim().isEmpty())
         result += " " + project;
      if (owner != null && !owner.trim().isEmpty())
         result += " " + owner;
      return result;
   }
}