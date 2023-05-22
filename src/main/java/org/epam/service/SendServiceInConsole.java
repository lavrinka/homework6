package org.epam.service;

import java.util.ArrayList;
import org.epam.TemplateFilling;
import org.epam.Value;
import org.epam.read.ReadFromConsole;
import org.epam.write.WriteToConsole;

public class SendServiceInConsole {

  public void sendMessage() {
    ReadFromConsole readFromConsole = new ReadFromConsole();
    TemplateFilling templateFilling = readFromConsole.readTemplateFromConsole();

    ArrayList<Value> values = readFromConsole.readValuesFromConsole();

    WriteToConsole writeToConsole = new WriteToConsole();
    String message = templateFilling.pasteValues(values);
    writeToConsole.writeMessageToConsole(message);
  }
}
