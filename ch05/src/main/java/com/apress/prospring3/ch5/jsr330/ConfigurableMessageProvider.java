/**
 * Created on Sep 26, 2011
 */
package com.apress.prospring3.ch5.jsr330;

import javax.inject.Inject;
import javax.inject.Named;
/*
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
*/
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
/*
 * !Spring configuration not JSR-330 * 
 * @Lazy(value=true) // XML: <bean ... lazy-init="true"/> * 
 * @Scope(value="prototype") // XML: <bean ... scope="prototype"/> * 
 * @DependsOn(value="messageProvider") // XML: <bean ... depends-on="messageProvider"/> 
 * @Required
 * By using JSR-330, you can ease the migration into other JSR-330 compatible IoC containers 
 * (e.g., JEE–6 compatible application servers, other DI containers such as Google Guice, etc.). 
 * However, Spring’s annotations are much more feature rich and flexible than JSR-330 annotations. 
 * Some main differences are highlighted here: 
 * • When using Spring’s @Autowired annotation, you can specify a required attribute to indicate that the DI must
 *   be fulfilled (you can also use Spring’s @Required annotation to declare this requirement), while for JSR-330’s
 *   @Inject annotation, there is no such equivalent. Moreover, Spring provides the @Qualifier annotation, which
 *   allows more fine-grained control for Spring to perform autowiring of dependencies based on qualifier name.
 * • JSR-330 supports only singleton and nonsingleton bean scopes, while Spring supports more scopes, which is
 *   very useful for web applications. 
 * • In Spring, you can use the @Lazy annotation to instruct Spring to instantiate the bean only when requested
 *   by the application. There’s no such equivalent in JSR-330. */
public class ConfigurableMessageProvider implements MessageProvider {
    
	private String message = "Default message";  

	public ConfigurableMessageProvider() {
	}	
	
	@Inject  // constructor injection
	@Named("message1")
	public ConfigurableMessageProvider(String message) {
		this.message = message;
	}	
	
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}	
	
}
