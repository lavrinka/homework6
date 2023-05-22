package org.epam;

import java.util.Objects;

public class Value {

  private String oldValue;
  private String newValue;

  public Value(String oldValue, String newValue) {
    this.oldValue = oldValue;
    this.newValue = newValue;
  }

  public Value(String oldValue) {
    this.oldValue = oldValue;
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Value)) {
      return false;
    }
    Value value = (Value) o;
    return Objects.equals(getOldValue(), value.getOldValue()) && Objects.equals(getNewValue(),
        value.getNewValue());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getOldValue(), getNewValue());
  }
}
