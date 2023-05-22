import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.epam.TemplateFilling;
import org.epam.Value;
import org.junit.jupiter.api.Test;

public class TemplateFillingTest {

  @Test
  void shouldParseTemplateWithValues(){
    TemplateFilling templateFilling = new TemplateFilling();
    String template = "This is template with name #{name}. Insert #{some value}";
    templateFilling.setTemplate(template);
    ArrayList<Value> values = new ArrayList<>();
    values.add(new Value("#{name}", "test"));
    values.add(new Value("#{some value}","#{value}"));
    String result = templateFilling.pasteValues(values);
    Assertions.assertThat(result).isNotEmpty().isEqualTo("This is template with name test. Insert #{value}");
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
    Assertions.assertThat(result).isNotEmpty().isEqualTo("This is a template with values #{value} and 123");

  }
}
