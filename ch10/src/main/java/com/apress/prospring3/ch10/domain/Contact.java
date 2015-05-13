/**
 * Created on Oct 12, 2011
 */
package com.apress.prospring3.ch10.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * @author Clarence
 *
 */
@Entity
@Table(name = "contact")
@NamedQueries({
	@NamedQuery(name="Contact.findAll",
	  query="select c from Contact c"), 
	@NamedQuery(name="Contact.findByIdWithDetail", 
	  query="select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h where c.id = :id"),
	@NamedQuery(name="Contact.findAllWithDetail", 
    query="select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h")
})
@SqlResultSetMapping(
		name="contactResult",
		entities=@EntityResult(entityClass=Contact.class)
)
public class Contact implements Serializable {	
  private static final long serialVersionUID = 1L;
  
  private Long id;
	private int version;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private Set<Hobby> hobbies = new HashSet<Hobby>();
	private Set<ContactTelDetail> contactTelDetails = new HashSet<ContactTelDetail>();

	public Contact() {
	}

	public Contact(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Contact(String firstName, String lastName, Date birthDate,
			Set<Hobby> hobbies, Set<ContactTelDetail> contactTelDetails) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.hobbies = hobbies;
		this.contactTelDetails = contactTelDetails;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Version
	@Column(name = "VERSION")
	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME")
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE")
	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

  /* For associations, the JPA specification states that, by default, the persistence providers must fetch the
   * association eagerly. However, for Hibernate’s JPA implementation, the default fetching strategy is still
   * lazy. So, when using Hibernate’s JPA implementation, you don’t need to explicitly define an association
   * as lazy fetching. The default fetching strategy of Hibernate is different from the JPA specification.  */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "contact_hobby_detail", 
	      joinColumns = @JoinColumn(name = "CONTACT_ID"), 
	      inverseJoinColumns = @JoinColumn(name = "HOBBY_ID"))
	public Set<Hobby> getHobbies() {
		return this.hobbies;
	}

	public void setHobbies(Set<Hobby> hobbies) {
		this.hobbies = hobbies;
	}
  
  /* orphanRemoval
   * has nothing to do with ON DELETE CASCADE. orphanRemoval is an entirely ORM-specific thing. It marks 
   * "child" entity to be removed when it's no longer referenced from the "parent" entity, e.g. when
   * you remove the child entity from the corresponding collection of the parent entity. ON DELETE CASCADE is
   * a database-specific thing, it deletes the "child" row in the database when the "parent" row is deleted.
   * The equivalent JPA mapping for the DDL ON DELETE CASCADE is cascade=CascadeType.REMOVE. Orphan removal
   * means that dependent entities are removed when the relationship to their "parent" entity is destroyed.
   * For example if a child is removed from a @OneToMany relationship without explicitly removing it in the
   * entity manager. The morale is to set orphanRemoval to true so long as you are certain that children of
   * that parent will not migrate to a different parent throughout their existence. Turning on orphanRemoval
   * also automatically adds REMOVE to cascade list.  */
	@OneToMany(mappedBy = "contact", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<ContactTelDetail> getContactTelDetails() {
		return this.contactTelDetails;
	}

	public void setContactTelDetails(Set<ContactTelDetail> contactTelDetails) {
		this.contactTelDetails = contactTelDetails;
	}
	
	public void addContactTelDetail(ContactTelDetail contactTelDetail) {
		contactTelDetail.setContact(this);
		getContactTelDetails().add(contactTelDetail);
	}
	
	public void removeContactTelDetail(ContactTelDetail contactTelDetail) {
		getContactTelDetails().remove(contactTelDetail);
	}		
	
	public String toString() {		
		return "Contact - Id: " + id + ", First name: " + firstName 
				+ ", Last name: " + lastName + ", Birthday: " + birthDate;
	}	

}
