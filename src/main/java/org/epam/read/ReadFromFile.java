package org.epam.read;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

  public ReadFromFile(String file) {
    this.filePath = Paths.get(file);
  }

  public TemplateFilling readTemplateFromFile() {
    try {
      List<String> read = Files.readAllLines(filePath);

      if (read.size() > 0) {
        templateFilling = new TemplateFilling();
        templateFilling.setTemplate(new String(read.get(0)
                                                   .getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        return templateFilling;
      } else {
        throw new TemplateIsEmpty();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Value> readValuesFromFile() {
    List<Value> newValues = new ArrayList<>();
    try {
      List<String> read = Files.readAllLines(filePath);

      if (read.size() > 1) {
        ReadingValues readingValues = new ReadingValues();
        List<Value> values = readingValues.readValues(templateFilling.getTemplate());
        for (int i = 1; i < read.size(); i++) {
          String[] entries = new String(read.get(i)
                                            .getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1).split(": ");
          String oldValue = entries[0];
          String newValue = entries[1];

          Value value = new Value(oldValue);
          if (values.contains(value)) {
            values.remove(value);
            value.setNewValue(newValue);
            newValues.add(value);
          }
        }

        if (!values.isEmpty()) {
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
