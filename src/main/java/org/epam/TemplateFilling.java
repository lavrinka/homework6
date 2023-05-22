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
    String result = template;
    for (Value value: values) {
      value.setOldValue(value.getOldValue().replace("{","\\{"));
      value.setOldValue(value.getOldValue().replace("}","\\}"));
      result = result.replaceFirst(value.getOldValue(), value.getNewValue());
    }
    return result;
  }

}
