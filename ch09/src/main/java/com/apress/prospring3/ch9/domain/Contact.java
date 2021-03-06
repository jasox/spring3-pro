/**
 * Created on Oct 12, 2011
 */
package com.apress.prospring3.ch9.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
	@NamedQuery(name="Contact.findByIdWithDetail", 
		query="select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h where c.id = :id"),
	@NamedQuery(name="Contact.findAllWithDetail", 
    query="select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h")
})
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
	
  /* The IDENTITY (we can use it directly within the annotation because of the import static statement)
   * strategy means that the id was generated by the backend during insert. (The ID column of the CONTACT  
   * table is the primary key, with AUTO_INCREMENT specified, which means that the value will be generated 
   * and assigned by the database during insert operation) */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

  /*
   * For the version attribute, we annotate it with @Version. This instructs Hibernate that we would like to
   * use an optimistic locking mechanism, using the version attribute as a control. Every time Hibernate
   * updates a record, it will compare the version of the entity instance to that of the record in the
   * database. If both versions are the same, it means that no one updated the data before, and Hibernate will
   * update the data and increment the version column. However, if the version is not the same, it means that
   * someone has updated the record before, and Hibernate will throw a StaleObjectStateException exception,
   * which Spring will translate to HibernateOptimisticLockingFailureException. In the example, we used an
   * integer for version control. Instead of an integer, Hibernate supports using a timestamp as well.
   * However, using an integer for version control is recommended because when using an integer, Hibernate
   * will always increment the version number by 1 after each update. When using timestamp, Hibernate will
   * update the latest timestamp after each update. A timestamp is slightly less safe, because two concurrent
   * transactions may both load and update the same item in the same millisecond.
   */
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

	@ManyToMany(fetch=FetchType.LAZY) // FetchType.LAZY is default	
	@JoinTable(name = "contact_hobby_detail", 
	      joinColumns = @JoinColumn(name = "CONTACT_ID"), 
	      inverseJoinColumns = @JoinColumn(name = "HOBBY_ID"))
	public Set<Hobby> getHobbies() {
		return this.hobbies;
	}

	public void setHobbies(Set<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

  /* The getter method of the attribute contactTelDetails is annotated with @OneToMany, which indicates the
   * one-to-many relationship with the ContactTelDetail class. Several attributes are passed to the annotation. 
   * The mappedBy attribute indicates the property in the ContactTelDetail class that provides the association 
   * (i.e., linked up by the foreign key definition in the CONTACT_TEL_DETAIL table). The cascade attribute 
   * means that update operation should cascade to the child. The orphanRemoval means that after the contact 
   * telephone details have been updated, those entries that no longer exist in the set should be deleted 
   * from the database. */
	@OneToMany(mappedBy = "contact", cascade=CascadeType.ALL, orphanRemoval=true)
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
		return "Contact - Id: " + id + ", First name: " + firstName + ", Last name: " + lastName + ", Birthday: " + birthDate;
	}	

}
