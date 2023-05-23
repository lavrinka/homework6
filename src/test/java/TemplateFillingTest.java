import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.epam.TemplateFilling;
import org.epam.Value;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

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
}
