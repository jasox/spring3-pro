/**
 * Created on Sep 26, 2011
 */
package com.apress.prospring3.ch5.jsr330;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * You will notice that all annotations belong to the javax.inject package, which is the JSR-330 standard.<br /> 
 * This class used @Named in two places. First, it can be used to declare an injectable bean (the same 
 * as the @Component or @Service annotation in Spring). In the listing, the @Named("messageProvider")
 * annotation specifies that the ConfigurableMessageProvider is an injectable bean and gives it the name
 * messageProvider, which is the same as the name attribute in Spring’s 'bean' tag. Second, we use constructor 
 * injection by using the @Inject annotation before the constructor that accepts a string value. 
 * Then, we use @Named to specify that we want to inject the value that had the name "message" assigned.
 * 
 * @author Clarence
 */                        // declare an injectable bean and gives it the name "messageProvider"
@Named("messageProvider")  // like < bean name = "messageProvider" ...  in XML
public class ConfigurableMessageProvider implements MessageProvider {
    
	private String message = "Default message";
  

	public ConfigurableMessageProvider() {
	}	
	
	@Inject  // constructor injection
	@Named("message")
	public ConfigurableMessageProvider(String message) {
		this.message = message;
	}	
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}	
	
}
