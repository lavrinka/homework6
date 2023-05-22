package org.epam.write;

import java.util.ArrayList;
import org.epam.ReadingValues;
import org.epam.TemplateFilling;
import org.epam.Value;
import org.epam.read.ReadFromConsole;

public class WriteToConsole {

  public void writeMessageToConsole(){
    ReadFromConsole readFromConsole = new ReadFromConsole();
    TemplateFilling templateFilling = new TemplateFilling();
    templateFilling.setTemplate(readFromConsole.readTemplateFromConsole());
    ReadingValues readingValues = new ReadingValues();
    ArrayList<Value> values = readingValues.readValues(templateFilling.getTemplate());
    readFromConsole.readValuesFromConsole(values);
    System.out.println(templateFilling.pasteValues(values));
  }

}
