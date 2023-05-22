import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.epam.ReadingValues;
import org.epam.Value;
import org.junit.jupiter.api.Test;

public class ReadFromFileTest {

  @Test
  void shouldBeAllValuesInAFile() {
    Path path = Paths.get("src/test/resources/fileWithTemplateWithWrongNumberOfValues.txt");

    ReadFromFile readFromFile = new ReadFromFile();
    String template = readFromFile.readTemplateFromFile();
    Assertions.assertThat(template).isEqualTo("This is a template with values #{val1} and #{val2}");

    ReadingValues readingValues = new ReadingValues();
    ArrayList<Value> values = readingValues.readValues(template);

    Assertions.assertThrows(InvalidNumberOfArgumentsException.class, () -> {
      readFromFile.readValuesFromConsole(values);
    });

  }
}
