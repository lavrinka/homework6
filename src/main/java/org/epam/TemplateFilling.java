package org.epam;

import java.util.ArrayList;

public class TemplateFilling {
  private String template;

  public void setTemplate(String template){
    this.template = template;
  }

  public String getTemplate(){
    return this.template;
  }

  public String pasteValues(ArrayList<Value> values){
    ArrayList<Value> copyValues = (ArrayList<Value>) values.clone();
    String result = template;
    for (Value value: copyValues) {
      String oldValue = value.getOldValue().replace("{","\\{").replace("}","\\}");
      result = result.replaceFirst(oldValue, value.getNewValue());
    }
    return result;
  }

}
