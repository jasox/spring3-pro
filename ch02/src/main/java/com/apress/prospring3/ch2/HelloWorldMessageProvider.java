/**
 * Created on Sep 11, 2011
 */
package com.apress.prospring3.ch2;

/**
 * @author Clarence
 *
 */
public class HelloWorldMessageProvider implements MessageProvider {

  @Override
	public String getMessage() {		
		return "Hello World!";
	}
	
}
