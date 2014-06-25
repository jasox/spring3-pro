/**
 * Created on Oct 17, 2011
 */
package com.apress.prospring3.ch10.service.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring3.ch10.domain.Contact;
import com.apress.prospring3.ch10.domain.Contact_;
import com.apress.prospring3.ch10.service.ContactService;

/**
 * The @Service annotation is to identify that it’s a Spring component that provides business services to
 * another layer and assigns the Spring bean the name "jpaContactService". <br/>
 * The @Repository annotation indicates that the class contains data access logic and instructs Spring 
 * to translate the vendor-specific exceptions to Spring’s <b>DataAccessException</b> hierarchy. Actually, 
 * you can use @Repository("jpaContactService") and get rid of the @Service annotation; the effect is the same. <br/> 
 * Here we just use the @Service annotation to indicate it belongs to the service layer (like @Controller 
 * indicates the component is in the web layer) because it is more developer-friendly. 
 * As you will be already familiar, the @Transactional annotation is for defining transaction requirements.
 * 
 * @author Clarence
 */
@Service("jpaContactService")
@Repository  // indicate to translate the vendor-specific exceptions to Spring’s DataAccessException hierarchy
@Transactional
public class ContactServiceImpl implements ContactService {

	private Log log = LogFactory.getLog(ContactServiceImpl.class);	
	
	@PersistenceContext // (unitName="...")
	private EntityManager em;
	
	@Transactional(readOnly=true)
	public List<Contact> findAll() {
		List<Contact> contacts = em.createNamedQuery("Contact.findAll", Contact.class).getResultList();
		return contacts;
	}

	@Transactional(readOnly=true)
	public List<Contact> findAllWithDetail() {
		List<Contact> contacts = em.createNamedQuery("Contact.findAllWithDetail", Contact.class).getResultList();
		return contacts;
	}

	@Transactional(readOnly=true)
	public Contact findById(Long id) {
		TypedQuery<Contact> query = em.createNamedQuery("Contact.findByIdWithDetail", Contact.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	public Contact save(Contact contact) {
		if (contact.getId() == null) { // Insert Contact
			log.info("Inserting new contact");
			em.persist(contact);
		} 
		else {                       // Update Contact
			em.merge(contact);
			log.info("Updating existing contact");
		}
		log.info("Contact saved with id: " + contact.getId());
		return contact;
	}

	public void delete(Contact contact) {
		Contact mergedContact = em.merge(contact);
		em.remove(mergedContact);
		log.info("Contact with id: " + contact.getId() + " deleted successfully");
	}
	
	final static String ALL_CONTACT_NATIVE_QUERY =
			"SELECT id, first_name, last_name, birth_date, version FROM contact";

	@Transactional(readOnly=true)
	public List<Contact> findAllByNativeQuery() {

		//return em.createNativeQuery(ALL_CONTACT_NATIVE_QUERY, Contact.class).getResultList();
		return em.createNativeQuery(ALL_CONTACT_NATIVE_QUERY, "contactResult").getResultList();
	}

	@Transactional(readOnly=true)
	public List<Contact> findByCriteriaQuery(String firstName, String lastName) {

		log.info("Finding contact for firstName: " + firstName + " and lastName: " + lastName);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Contact> criteriaQuery = cb.createQuery(Contact.class);
		Root<Contact> contactRoot = criteriaQuery.from(Contact.class);
		contactRoot.fetch(Contact_.contactTelDetails, JoinType.LEFT);
		contactRoot.fetch(Contact_.hobbies, JoinType.LEFT);

		criteriaQuery.select(contactRoot).distinct(true);
		
		Predicate criteria = cb.conjunction();
		
		// First Name
		if (firstName != null) {
			Predicate p = cb.equal(contactRoot.get(Contact_.firstName), firstName);
			criteria = cb.and(criteria, p);
		}
		
		// Last Name
		if (lastName != null) {
			Predicate p = cb.equal(contactRoot.get(Contact_.lastName), lastName);
			criteria = cb.and(criteria, p);
		}		
		
		criteriaQuery.where(criteria);
		List<Contact> result = em.createQuery(criteriaQuery).getResultList();
		return result;
		
	}

	public List<Contact> findByFirstName(String firstName) {
		// Not implemented
		return null;
	}

	public List<Contact> findByFirstNameAndLastName(String firstName,	String lastName) {
		// Not implemented
		return null;
	}	
	
}
