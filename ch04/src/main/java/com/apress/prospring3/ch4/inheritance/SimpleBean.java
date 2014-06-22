/*
 * Created on 22-Sep-2011
 */
package com.apress.prospring3.ch4.inheritance;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * @author Clarence
 */
public class SimpleBean {

  public String name;
  public int    age;

  // ---------------------------------------------------------------------------

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String toString() {
    return "Name: " + name + "\nAge: " + age;
  }

  // ---------------------------------------------------------------------------

  public static void main(String[] args) {

    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    ctx.load("classpath:app-context-xml.xml");

    SimpleBean parentBean = (SimpleBean) ctx.getBean("inheritParent");
    SimpleBean childBean  = (SimpleBean) ctx.getBean("inheritChild");

    System.out.println("Parent:\n" + parentBean);
    System.out.println("Child: \n" + childBean);
  }

}
