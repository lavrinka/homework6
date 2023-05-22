import java.nio.file.Path;
import java.nio.file.Paths;
import org.assertj.core.api.Assertions;
import org.epam.TemplateFilling;
import org.epam.exception.InvalidNumberOfArgumentsException;
import org.epam.read.ReadFromFile;
import org.junit.jupiter.api.Test;

public class ReadFromFileTest {

  @Test
  void shouldBeAllValuesInAFile() {
    Path path = Paths.get("src/test/resources/fileWithTemplateWithWrongNumberOfValues.txt");

    ReadFromFile readFromFile = new ReadFromFile(path);
    TemplateFilling template = readFromFile.readTemplateFromFile();
    Assertions.assertThat(template.getTemplate())
              .isEqualTo("This is a template with values #{val1} and #{val2}");

    Assertions.assertThatThrownBy(() -> readFromFile.readValuesFromFile())
              .isInstanceOf(InvalidNumberOfArgumentsException.class);
  }
}
