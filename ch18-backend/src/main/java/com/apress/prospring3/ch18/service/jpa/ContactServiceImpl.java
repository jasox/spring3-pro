/**
 * Created on Dec 28, 2011
 */
package com.apress.prospring3.ch18.service.jpa;

import com.apress.prospring3.ch18.domain.Contact;
import com.apress.prospring3.ch18.repository.ContactRepository;
import com.apress.prospring3.ch18.service.ContactService;
import com.google.common.collect.Lists;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Clarence
 *
 */
@Service("contactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {

  final static Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);
  
  @Autowired
  private ContactRepository contactRepository;  

  @Transactional(readOnly = true)
  public List<Contact> findAll() {
    logger.info("Finding all contacts");
    return Lists.newArrayList(contactRepository.findAll());
  }

  @Transactional(readOnly = true)
  public Contact findById(Long id) {
    return contactRepository.findOne(id);
  }

  @Transactional(readOnly = true)
  public Contact findByIdWithDetail(Long id) {
    return contactRepository.findByIdWithDetail(id);
  }

  public Contact save(Contact contact) {
    return contactRepository.save(contact);
  }
  
}
