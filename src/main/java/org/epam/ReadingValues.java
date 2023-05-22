package org.epam;

import java.util.ArrayList;

public class ReadingValues {
  public ArrayList<Value> readValues(String template){
    ArrayList<Value> values = new ArrayList<>();
    int start = template.indexOf("#{");
    int end = template.indexOf("}",start);
    while(start!=-1 && end !=-1){
      String oldValue = template.substring(start, end+1);
      start = template.indexOf("#{", end);
      end = template.indexOf("}",start);
      values.add(new Value(oldValue));
    }
    return values;
  }
}
