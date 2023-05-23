import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.epam.TemplateFilling;
import org.epam.Value;
import org.epam.read.ReadFromConsole;
import org.epam.read.ReadFromFile;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.mockito.Mockito;

public class TemplateFillingTest {

  @Test
  void shouldParseTemplateWithValues() {
    TemplateFilling templateFilling = new TemplateFilling();
    String template = "This is template with name #{name}. Insert #{some value}";
    templateFilling.setTemplate(template);
    ArrayList<Value> values = new ArrayList<>();
    values.add(new Value("#{name}", "test"));
    values.add(new Value("#{some value}", "#{value}"));
    String result = templateFilling.pasteValues(values);
    Assertions.assertThat(result)
              .isNotEmpty()
              .isEqualTo("This is template with name test. Insert #{value}");
  }

  //Mock reading from file/console
  @Test
  void shouldParseTemplateWithValuesWithMockReadingFromConsole() {

    ReadFromConsole readFromConsole = Mockito.mock(ReadFromConsole.class);
    TemplateFilling templateFilling = new TemplateFilling();
    templateFilling.setTemplate("This is template with name #{name}. Insert #{some value}");
    Mockito.when(readFromConsole.readTemplateFromConsole()).thenReturn(templateFilling);

    ArrayList<Value> values = new ArrayList<>();
    values.add(new Value("#{name}", "test"));
    values.add(new Value("#{some value}", "#{value}"));
    Mockito.when(readFromConsole.readValuesFromConsole()).thenReturn(values);

    readFromConsole.readTemplateFromConsole();

    TemplateFilling templateFillingMock = readFromConsole.readTemplateFromConsole();
    ArrayList<Value> valuesMock = readFromConsole.readValuesFromConsole();
    String result = templateFillingMock.pasteValues(valuesMock);
    Assertions.assertThat(result)
              .isNotEmpty()
              .isEqualTo("This is template with name test. Insert #{value}");
  }


  //Dynamic tests + Implement meta annotations and filtering
  @Slow
  @TestFactory
  Stream<DynamicTest> dynamicTestsIgnoreValuesNotFromTemplate() {

    ArrayList<Value> values = new ArrayList<>();
    values.add(new Value("#{val1}", "#{value}"));
    values.add(new Value("#{some value}", "value to write"));
    values.add(new Value("#{val2}", "123"));
    values.add(new Value("#{val3}", "345"));

    List<String> templates = Arrays.asList("This is a template with values #{val1} and #{val2}",
        "This is a template with values #{val3} and #{val2}");

    List<String> output = Arrays.asList("This is a template with values #{value} and 123",
        "This is a template with values 345 and 123");

    return templates.stream()
                    .map(template -> DynamicTest.dynamicTest("IgnoreValuesNotFromTemplate: " + template, () -> {
                      int id = templates.indexOf(template);
                      TemplateFilling templateFilling = new TemplateFilling();
                      templateFilling.setTemplate(template);
                      String result = templateFilling.pasteValues(values);
                      Assertions.assertThat(result)
                                .isNotEmpty()
                                .isEqualTo(output.get(id));
                    }));
  }

  @Test
  void shouldIgnoreValuesNotFromTemplate() {
    TemplateFilling templateFilling = new TemplateFilling();
    String template = "This is a template with values #{val1} and #{val2}";
    templateFilling.setTemplate(template);
    ArrayList<Value> values = new ArrayList<>();
    values.add(new Value("#{val1}", "#{value}"));
    values.add(new Value("#{some value}", "value to write"));
    values.add(new Value("#{val2}", "123"));
    String result = templateFilling.pasteValues(values);
    Assertions.assertThat(result)
              .isNotEmpty()
              .isEqualTo("This is a template with values #{value} and 123");

  }

  //Use partial mock + Use spy
  @Test
  void shouldPasteValuesInTemplateWithLatinWithPartialMockOfReading() {
    String inputFile = "src/test/resources/fileWithLatin.txt";
    ReadFromFile readFromFile = Mockito.spy(new ReadFromFile(inputFile));

    TemplateFilling templateFilling = readFromFile.readTemplateFromFile();

    ArrayList<Value> values = new ArrayList<>();
    values.add(new Value("#{val1}", new String("#{value}".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1)));
    values.add(new Value("#{val2}", new String("123Ñ".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1)));

    Mockito.doReturn(values).when(readFromFile).readValuesFromFile();
    String message = new String("This is a templateÑ with values #{value} and 123Ñ".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);

    String result = templateFilling.pasteValues(values);
    Assertions.assertThat(result)
              .isNotEmpty()
              .isEqualTo(message);

  }
}
