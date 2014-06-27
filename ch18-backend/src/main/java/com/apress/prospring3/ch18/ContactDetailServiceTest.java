/**
 * Created on Dec 21, 2011
 */
package com.apress.prospring3.ch18;

import com.apress.prospring3.ch18.domain.Contact;
import com.apress.prospring3.ch18.domain.Hobby;
import com.apress.prospring3.ch18.service.ContactService;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * @author Clarence
 *
 */
public class ContactDetailServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:jpa-app-context.xml");
		ctx.refresh();
		
		System.out.println("App context initialized successfully");
		
		ContactService contactService = ctx.getBean("contactService", ContactService.class);
		
		Contact contact = contactService.findByIdWithDetail(1L);	
		//Contact contact = contactService.findById(1L);
		
		System.out.println(contact);
		for (Hobby hobby: contact.getHobbies()) {
			System.out.println(hobby.getHobbyId());
		}
		
	}

}
