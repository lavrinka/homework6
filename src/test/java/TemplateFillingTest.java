import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TemplateFillingTest {

  @Test
  void shouldParseTemplateWithValues(){
    TemplateFilling templateFilling = new TemplateFilling();
    String template = "This is template with name #{name}. Insert #{some value}";
    templateFilling.setTemplate(template);
    ArrayList<String> values = new ArrayList<>();
    values.add("test");
    values.add("#{value}");
    String result = templateFilling.pasteValues(values);
    Assertions.assertThat(result).isNotEmpty().isEqualTo("This is template with name test. Insert #{value}");
  }
}
