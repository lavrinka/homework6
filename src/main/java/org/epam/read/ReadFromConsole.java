package org.epam.read;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.epam.ReadingValues;
import org.epam.TemplateFilling;
import org.epam.Value;

public class ReadFromConsole {

  private TemplateFilling templateFilling;

  public TemplateFilling getTemplateFilling(){
    return templateFilling;
  }

  public TemplateFilling readTemplateFromConsole() {
    try {
      InputStreamReader ireader = new InputStreamReader(System.in);
      BufferedReader bufr = new BufferedReader(ireader);
      System.out.println("Template is:");
      String template = bufr.readLine();
      templateFilling = new TemplateFilling();
      templateFilling.setTemplate(template);
      return templateFilling;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public ArrayList<Value> readValuesFromConsole() {
    ReadingValues readingValues = new ReadingValues();
    ArrayList<Value> values = readingValues.readValues(templateFilling.getTemplate());
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
