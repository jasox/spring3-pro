/*
 * Created on Sep 26, 2011
 */
package com.apress.prospring3.ch5.context;

import java.util.Locale;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * @author clarence
 */
public class MessageSourceDemo {

  public static void main(String[] args) {

    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    ctx.load("classpath:appContext/messageSource.xml");
    ctx.refresh();

    Locale englishLocale = Locale.ENGLISH;
    Locale czechLocale   = new Locale("cs", "CZ");

    System.out.println(ctx.getMessage("msg", null, englishLocale) + ", locale : " + englishLocale);
    System.out.println(ctx.getMessage("msg", null, czechLocale)   + ", locale : " + czechLocale);

    System.out.println(ctx.getMessage("nameMsg", new Object[] { "Clarence", "Ho" }, englishLocale));
    System.out.println(ctx.getMessage("nameMsg", new Object[] { "Clarence", "Ho" }, czechLocale));
  }
}