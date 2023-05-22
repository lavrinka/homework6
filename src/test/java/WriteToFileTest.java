import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.epam.TemplateFilling;
import org.epam.Value;
import org.epam.read.ReadFromFile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class WriteToFileTest {

  @Test
  void shouldIgnoreValuesNotFromTemplate() {
    Path path = Paths.get("fileWithTemplateWithValuesNotForTemplate.txt");

    ReadFromFile readFromFile = new ReadFromFile(path);
    readFromFile.readTemplateFromFile();
    ArrayList<Value> values = readFromFile.readValuesFromFile();
    WriteToFile writeToFile = new WriteToFile();
    Assertions.assertThat(writeToFile.writeMessageToFile())
              .isEqualTo("This is a template with values #{value} and 123");
  }

  @Test
  void shouldUseLatinForWrite() {
    Path path = Paths.get("fileWithLatin.txt");

    ReadFromFile readFromFile = new ReadFromFile(path);
    readFromFile.readTemplateFromFile();
    ArrayList<Value> values = readFromFile.readValuesFromFile();
    WriteToFile writeToFile = new WriteToFile();
    Assertions.assertThat(writeToFile.writeMessageToFile())
              .isEqualTo("This is a template with values #{value} and 123Ñ");
  }

}
