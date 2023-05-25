package org.epam;

import java.util.ArrayList;
import java.util.List;

public class TemplateFilling {
  private String template;

  public void setTemplate(String template){
    this.template = template;
  }

  public String getTemplate(){
    return this.template;
  }

  public String pasteValues(List<Value> values){
    List<Value> copyValues = new ArrayList<>(values);
    String result = template;
    for (Value value: copyValues) {
      String oldValue = value.getOldValue().replace("{","\\{").replace("}","\\}");
      result = result.replaceFirst(oldValue, value.getNewValue());
    }
    return result;
  }

}
