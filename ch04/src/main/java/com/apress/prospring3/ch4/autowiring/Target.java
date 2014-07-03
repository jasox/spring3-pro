/*
 * Created on Sep 22, 2011
 */
package com.apress.prospring3.ch4.autowiring;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * @author clarence
 */
public class Target {

  private Foo foo;
  private Foo foo2;
  private Bar bar;
  
  //----------------------------------------------------------------------------

  public Target() {
  }

  public Target(Foo foo) {
    this.foo = foo;
    System.out.println("Target(Foo) called ");
  }

  public Target(Foo foo, Bar bar) {
    this.foo = foo;
    this.bar = bar;
    System.out.println("Target(Foo, Bar) called");
  }
  
  public Target(Foo foo, Foo foo2, Bar bar) {
    this.foo  = foo;
    this.foo2 = foo2;
    this.bar  = bar;
    System.out.println("Target(Foo, Foo, Bar) called");
  }
  
  //----------------------------------------------------------------------------

  public void setFoo(Foo foo) {
    this.foo = foo;
    System.out.println("Property foo set ");
  }

  public void setFoo2(Foo foo) {
    this.foo2 = foo;
    System.out.println("Property foo2 set");
  }

  public void setBar(Bar bar) {
    this.bar = bar;
    System.out.println("Property bar set");
  }  
  
  //----------------------------------------------------------------------------
  
  public void showReferences() {    
    System.out.println(" foo and foo2 have the same references? " + (foo == foo2));
    System.out.println(" foo and foo2 have the same value     ? " + (foo.equals(foo2)));     
  }

  public static void main(String[] args) {
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    ctx.load("classpath:autowiring.xml");
    ctx.refresh();

    Target t = null;

    System.out.println("\nUsing byName:\n");
    t = (Target) ctx.getBean("targetByName");
    t.showReferences();

    System.out.println("\nUsing byType:\n");
    t = (Target) ctx.getBean("targetByType");
    t.showReferences();

    System.out.println("\nUsing constructor:\n");
    t = (Target) ctx.getBean("targetConstructor");
    t.showReferences();
  }
  
}
