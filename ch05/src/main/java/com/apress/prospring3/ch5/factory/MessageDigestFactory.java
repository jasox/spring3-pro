/*
 * Created on Sep 25, 2011
 */
package com.apress.prospring3.ch5.factory;

import java.security.MessageDigest;

/**
 * Sometimes you need to instantiate JavaBeans that were provided by a non-Spring-powered third-party
 * application. You don’t know how to instantiate that class, but you know that the third-party application
 * did provide a class that can be used to get an instance of the JavaBean that your Spring application required. 
 * In this case, Spring bean’s factory-bean and factory-method attributes in the 'bean' tag can be used.
 * @author clarence
 */
// factory-bean
public class MessageDigestFactory {

  private String algorithmName = "MD5";

  // factory-method
  public MessageDigest createInstance() throws Exception {
    return MessageDigest.getInstance(algorithmName);
  }

  public void setAlgorithmName(String algorithmName) {
    this.algorithmName = algorithmName;
  }

}
