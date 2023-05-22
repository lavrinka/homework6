package org.epam.read;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.epam.ReadingValues;
import org.epam.TemplateFilling;
import org.epam.Value;
import org.epam.exception.InvalidNumberOfArgumentsException;
import org.epam.exception.TemplateIsEmpty;

public class ReadFromFile {

  private final Path filePath;

  private TemplateFilling templateFilling;

  public TemplateFilling getTemplateFilling() {
    return templateFilling;
  }

  public ReadFromFile(Path filePath) {
    this.filePath = filePath;
  }

  public TemplateFilling readTemplateFromFile() {
    try {
      List<String> read = Files.readAllLines(filePath);

      if (read.size() > 0) {
        templateFilling = new TemplateFilling();
        templateFilling.setTemplate(read.get(0));
        return templateFilling;
      } else {
        throw new TemplateIsEmpty();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public ArrayList<Value> readValuesFromFile() {
    ArrayList<Value> newValues = new ArrayList<>();
    try {
      List<String> read = Files.readAllLines(filePath);

      if (read.size() > 1) {
        ReadingValues readingValues = new ReadingValues();
        ArrayList<Value> values = readingValues.readValues(templateFilling.getTemplate());
        for (int i = 1; i < read.size(); i++) {
          String[] entries = read.get(i)
                                 .split(": ");
          String oldValue = entries[0];
          String newValue = entries[1];

          Value value = new Value(oldValue);
          if (values.contains(value)) {
            values.remove(value);
            value.setNewValue(newValue);
            newValues.add(value);
          }
        }

        if(!values.isEmpty()){
          throw new InvalidNumberOfArgumentsException();
        }

      } else {
        throw new TemplateIsEmpty();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return newValues;
  }

}
