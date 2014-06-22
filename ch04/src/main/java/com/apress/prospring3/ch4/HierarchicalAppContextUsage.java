/**
 * Created on Sep 21, 2011
 */
package com.apress.prospring3.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * @author Clarence
 *
 */
public class HierarchicalAppContextUsage {

  public static void main(String[] args) {

    GenericXmlApplicationContext parentCtx = new GenericXmlApplicationContext();
    parentCtx.load("classpath:parent.xml");
    parentCtx.refresh();

    GenericXmlApplicationContext childCtx = new GenericXmlApplicationContext();
    childCtx.load("classpath:app-context-xml.xml");
    childCtx.setParent(parentCtx);
    childCtx.refresh();

    SimpleTarget target1 = (SimpleTarget) childCtx.getBean("target1");
    SimpleTarget target2 = (SimpleTarget) childCtx.getBean("target2");
    SimpleTarget target3 = (SimpleTarget) childCtx.getBean("target3");

    System.out.println(target1.getVal());
    System.out.println(target2.getVal());
    System.out.println(target3.getVal());

  }

}
