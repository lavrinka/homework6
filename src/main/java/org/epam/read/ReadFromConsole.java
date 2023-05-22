package org.epam.read;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.epam.Value;

public class ReadFromConsole {

  public String readTemplateFromConsole() {
    try {
      InputStreamReader ireader = new InputStreamReader(System.in);
      BufferedReader bufr = new BufferedReader(ireader);
      System.out.println("Template is:");
      String template = bufr.readLine();
      return template;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public ArrayList<Value> readValuesFromConsole(ArrayList<Value> values) {
    try {
      for (Value value : values) {
        InputStreamReader ireader = new InputStreamReader(System.in);
        BufferedReader bufr = new BufferedReader(ireader);
        System.out.println(value.getOldValue() + ":");
        value.setNewValue(bufr.readLine());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return values;
  }
}
