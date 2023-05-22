package org.epam;

public class Value {

  private String oldValue;

  public Value(String oldValue, String newValue) {
    this.oldValue = oldValue;
    this.newValue = newValue;
  }

  private String newValue;

  public String getOldValue() {
    return oldValue;
  }

  public void setOldValue(String oldValue) {
    this.oldValue = oldValue;
  }

  public String getNewValue() {
    return newValue;
  }

  public void setNewValue(String newValue) {
    this.newValue = newValue;
  }

}
