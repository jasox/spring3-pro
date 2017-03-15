/**
 * Created on Sep 20, 2011
 */
package com.apress.prospring3.ch4;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.FileSystemResource;

/**
 * @author Clarence
 *
 */
public class XmlConfigWithBeanFactory {

  public static void main(String[] args) {
    /*
    DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader    rdr     = new XmlBeanDefinitionReader(factory);
    */
    BeanFactory             factory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader rdr     = new XmlBeanDefinitionReader((BeanDefinitionRegistry)factory);
    
    rdr.loadBeanDefinitions(new FileSystemResource("ch04/src/main/resources/xmlBeanFactory.xml"));
    Oracle oracle = (Oracle) factory.getBean("oracle");
    System.out.println(oracle.defineMeaningOfLife());
  }

}
