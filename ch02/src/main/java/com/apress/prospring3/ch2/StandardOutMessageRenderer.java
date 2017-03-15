/**
 * Created on Sep 11, 2011
 */
package com.apress.prospring3.ch2;

/**
 * @author Clarence
 *
 */
public class StandardOutMessageRenderer implements MessageRenderer {

  private MessageProvider messageProvider = null;

  @Override
  public void render() {
    if (messageProvider == null) {
      //throw new RuntimeException("You must set the property messageProvider of class:" + StandardOutMessageRenderer.class.getName());
      throw new IllegalStateException("You must set the property messageProvider of class:" + StandardOutMessageRenderer.class.getName());
    }
    System.out.println(messageProvider.getMessage());
  }

  @Override
  public void setMessageProvider(MessageProvider provider) {
    this.messageProvider = provider;
  }

  @Override
  public MessageProvider getMessageProvider() {
    return this.messageProvider;
  }

}
