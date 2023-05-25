package org.epam.read;

import java.io.Console;
import java.util.List;
import org.epam.ReadingValues;
import org.epam.TemplateFilling;
import org.epam.Value;

public class ReadFromConsole {

  private TemplateFilling templateFilling;

  public TemplateFilling getTemplateFilling() {
    return templateFilling;
  }

  public TemplateFilling readTemplateFromConsole() {
    Console console = System.console();

    if (console == null) {
      throw new RuntimeException("No console available");
    }

    String template = console.readLine("Template is:");
    templateFilling = new TemplateFilling();
    templateFilling.setTemplate(template);
    return templateFilling;
  }

  public List<Value> readValuesFromConsole() {
    Console console = System.console();

    if (console == null) {
      throw new RuntimeException("No console available");
    }

    ReadingValues readingValues = new ReadingValues();
    List<Value> values = readingValues.readValues(templateFilling.getTemplate());
    for (Value value : values) {
      value.setNewValue(console.readLine(value.getOldValue() + ":"));
    }
    return values;
  }
}
