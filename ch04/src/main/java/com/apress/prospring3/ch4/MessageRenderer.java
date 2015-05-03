/**
 * Created on Sep 11, 2011
 */
package com.apress.prospring3.ch4;

/**
 * @author Clarence
 *
 */
public interface MessageRenderer {

	public void render();
	
	/* However, you don’t need to define setter method in	the business interface. Instead, you can define the method 
	 * in the classes implementing the business	interface. While programming in this way, all recent IoC containers, 
	 * Spring included, can work with the	component in terms of the business interface but still provide the dependencies 
	 * of the implementing class */
	
	//public void setMessageProvider(MessageProvider provider);
	
	//public MessageProvider getMessageProvider();
	
}
