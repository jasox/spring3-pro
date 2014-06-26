/**
 * Created on Nov 7, 2011
 */
package com.apress.prospring3.ch13.service.annotation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring3.ch13.domain.Contact;
import com.apress.prospring3.ch13.repository.ContactRepository;
import com.apress.prospring3.ch13.service.ContactService;
import com.google.common.collect.Lists;

/**
 * @author Clarence
 */
/*
 * When using annotation-based transaction management, the only annotation that we need to deal with is the 
 * @Transactional annotation. In this case the @Transactional annotation was applied at the class level, which
 * means that, by default, Spring will ensure that a transaction is present before the execution of each
 * method within the class. The @Transactional annotation supports a number of attributes that you can provide
 * to override the default behavior. Table shows the available attributes, together with the possible and
 * default values. 
 * Attribute Name    Default Value   Possible Values 
 * propagation Propagation.REQUIRED  Propagation.REQUIRED 
 *                                   Propagation.SUPPORTS 
 *                                   Propagation.MANDATORY 
 *                                   Propagation.REQUIRES_NEW
 *                                   Propagation.NOT_SUPPORTED 
 *                                   Propagation.NEVER 
 *                                   Propagation.NESTED 
 * isolation Isolation.DEFAULT (Default isolation level of the underlying resource) Isolation.DEFAULT 
 *                                                                                  Isolation.READ_UNCOMMITTED
 *                                                                                  Isolation.READ_COMMITTED 
 *                                                                                  Isolation.REPEATABLE_READ 
 *                                                                                  Isolation.SERIALIZABLE 
 * timeout TransactionDefinition.TIMEOUT_DEFAULT (Default transaction timeout in seconds of the underlying resource)
 *                        An integer value larger than zero; indicates the number in seconds for transaction timeout 
 * readOnly false True false 
 * rollbackFor            Exception classes for which transaction will be rolled back N/A 
 * rollbackForClassName   Exception class names for which transaction will be rolled back N/A 
 * noRollbackFor          Exception classes for which transaction will not be rolled back N/A 
 * noRollbackForClassName Exception class names for which transaction will not be rolled back N/A 
 * value "" (A qualifier value for the specified transaction)
 */
@Service("contactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	@Transactional(readOnly = true)
	public List<Contact> findAll() {
		return Lists.newArrayList(contactRepository.findAll());
	}

	@Transactional(readOnly = true)
	public Contact findById(Long id) {
		return contactRepository.findOne(id);
	}
	
	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}

	@Transactional(propagation = Propagation.NEVER, readOnly = true)
	//@Transactional(readOnly = true)
	public long countAll() {
		return contactRepository.countAllContacts();
	}

}
