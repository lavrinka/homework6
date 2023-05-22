import java.nio.charset.StandardCharsets;
import org.assertj.core.api.Assertions;
import org.epam.TemplateFilling;
import org.epam.exception.InvalidNumberOfArgumentsException;
import org.epam.read.ReadFromFile;
import org.junit.jupiter.api.Test;

public class ReadFromFileTest {

  @Test
  void shouldBeAllValuesInAFile() {
    String inputFile = "src/test/resources/fileWithTemplateWithWrongNumberOfValues.txt";

    ReadFromFile readFromFile = new ReadFromFile(inputFile);
    TemplateFilling template = readFromFile.readTemplateFromFile();
    Assertions.assertThat(template.getTemplate())
              .isEqualTo("This is a template with values #{val1} and #{val2}");

    Assertions.assertThatThrownBy(() -> readFromFile.readValuesFromFile())
              .isInstanceOf(InvalidNumberOfArgumentsException.class);
  }

  @Test
  void shouldBeReadLatinFromFile() {
    String inputFile = "src/test/resources/fileWithLatin.txt";

    ReadFromFile readFromFile = new ReadFromFile(inputFile);
    TemplateFilling template = readFromFile.readTemplateFromFile();
    String message = "This is a templateÑ with values #{val1} and #{val2}";

    Assertions.assertThat(template.getTemplate())
              .isEqualTo(new String(message.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
  }
}
