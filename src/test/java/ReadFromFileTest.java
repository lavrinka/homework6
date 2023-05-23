import java.nio.charset.StandardCharsets;
import org.assertj.core.api.Assertions;
import org.epam.TemplateFilling;
import org.epam.exception.InvalidNumberOfArgumentsException;
import org.epam.read.ReadFromFile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ReadFromFileTest {

  //@Parameterized runner
  @ParameterizedTest
  @ValueSource(strings = {"src/test/resources/fileWithTemplateWithWrongNumberOfValues.txt",
      "src/test/resources/fileWithTemplateWithNotAllValues.txt"})
  void shouldBeAllValuesInAFile(String inputFile) {
    ReadFromFile readFromFile = new ReadFromFile(inputFile);
    readFromFile.readTemplateFromFile();

    Assertions.assertThatThrownBy(() -> readFromFile.readValuesFromFile())
              .isInstanceOf(InvalidNumberOfArgumentsException.class)
              .hasMessageContaining("Invalid number of params");
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
