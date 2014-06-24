/**
 * Created on Sep 11, 2011
 */
package com.apress.prospring3.ch5.jsr330;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * we used @Named to define that it’s an injectable bean. 
 * Notice the @Singleton annotation. It’s worth noting that in the JSR-330 standard, a bean’s 
 * default scope is <b>nonsingleton</b>, which is like Spring’s prototype scope. So, in a JSR-330 
 * environment, if you want your bean to be a singleton, you need to use the @Singleton annotation. 
 * However, using this annotation in Spring actually doesn’t have any effect, because Spring’s default 
 * scope for bean instantiation is already singleton. We just put it here for demonstration, and it’s 
 * worth noting the difference between Spring and other JSR-330 compatible containers.
 * 
 * @author Clarence
 */
@Named("messageRenderer")
@Singleton
public class StandardOutMessageRenderer implements MessageRenderer {

  @Inject
  @Named("messageProvider")
  private MessageProvider messageProvider = null;
  

  public void render() {
    if (messageProvider == null) {
      throw new RuntimeException("You must set the property messageProvider of class:"
          + StandardOutMessageRenderer.class.getName());
    }

    System.out.println(messageProvider.getMessage());
  }

  public void setMessageProvider(MessageProvider provider) {
    this.messageProvider = provider;
  }

  public MessageProvider getMessageProvider() {
    return this.messageProvider;
  }

}
