package org.epam;

import java.util.ArrayList;
import java.util.List;

public class ReadingValues {
  public List<Value> readValues(String template){
    List<Value> values = new ArrayList<>();
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
