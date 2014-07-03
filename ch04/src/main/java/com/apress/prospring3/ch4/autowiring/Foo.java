/*
 * Created on Sep 22, 2011
 */
package com.apress.prospring3.ch4.autowiring;

/**
 * @author clarence
 */
public class Foo {
  
  private String someValue = "foo";

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((someValue == null) ? 0 : someValue.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Foo other = (Foo) obj;
    if (someValue == null) {
      if (other.someValue != null)
        return false;
    }
    else if (!someValue.equals(other.someValue))
      return false;
    return true;
  } 
  
}
