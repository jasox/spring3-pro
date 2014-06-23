/*
 * Created on Sep 25, 2011
 */
package com.apress.prospring3.ch5.factory;

import java.security.MessageDigest;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author clarence
 */
public class MessageDigestFactoryBean implements FactoryBean<MessageDigest> {

  private String        algorithmName = "MD5";
  private MessageDigest messageDigest = null;
  

  @Override
  public MessageDigest getObject() throws Exception {
    return messageDigest;
  }

  @Override
  public Class<MessageDigest> getObjectType() {
    return MessageDigest.class;
  }

  /**
   * The isSingleton() property allows you to inform Spring whether the FactoryBean is managing a singleton
   * instance. Remember that by setting the singleton attribute of the FactoryBean’s &lt bean &gt tag, you
   * tell Spring about the singleton status of the FactoryBean itself, not the objects it is returning.   
   */
  @Override
  public boolean isSingleton() {
    return true;
  }
  
  @PostConstruct
  public void init() throws Exception {
    messageDigest = MessageDigest.getInstance(algorithmName);
  }

  public void setAlgorithmName(String algorithmName) {
    this.algorithmName = algorithmName;
  }

}
