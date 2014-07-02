/**
 * Created on Sep 11, 2011
 */
package com.apress.prospring3.ch2;

/**
 * @author Clarence
 *
 */
public interface MessageRenderer {

	public void render();	
	
	public MessageProvider getMessageProvider();	
	public void setMessageProvider(MessageProvider provider);
	
}
