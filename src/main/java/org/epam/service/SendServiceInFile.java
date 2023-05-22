package org.epam.service;

import java.util.ArrayList;
import org.epam.TemplateFilling;
import org.epam.Value;
import org.epam.read.ReadFromFile;
import org.epam.write.WriteToFile;

public class SendServiceInFile {

  public void sendMessage(String inputFile, String outputFile) {
    ReadFromFile readFromFile = new ReadFromFile(inputFile);
    TemplateFilling templateFilling = readFromFile.readTemplateFromFile();

    ArrayList<Value> values = readFromFile.readValuesFromFile();

    WriteToFile writeToFile = new WriteToFile(outputFile);
    String message = templateFilling.pasteValues(values);
    writeToFile.writeMessageToFile(message);
  }
}
