import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.charset.StandardCharsets;
import org.assertj.core.api.Assertions;
import org.epam.TemplateFilling;
import org.epam.exception.InvalidNumberOfArgumentsException;
import org.epam.read.ReadFromFile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

//Create custom extension (jUnit5) to output test execution information to file
@ExtendWith({CustomWriteToFileExtention.class})
public class ReadFromFileTest {

  //@Parameterized runner
  @ParameterizedTest
  @ValueSource(strings = {"src/test/resources/fileWithTemplateWithWrongNumberOfValues.txt",
      "src/test/resources/fileWithTemplateWithNotAllValues.txt"})
  void shouldBeAllValuesInAFile(String inputFile) {
    System.out.println("-------------------------");
    System.out.println("start of shouldBeAllValuesInAFile with input: " + inputFile);
    ReadFromFile readFromFile = new ReadFromFile(inputFile);
    readFromFile.readTemplateFromFile();

    Assertions.assertThatThrownBy(() -> readFromFile.readValuesFromFile())
              .isInstanceOf(InvalidNumberOfArgumentsException.class)
              .hasMessageContaining("Invalid number of params");
    System.out.println("end of shouldBeAllValuesInAFile");
    System.out.println("-------------------------");
  }

  //Using ExpectedException rule to check exceptions + Assertion mechanism
  @Test
  void shouldBeAllValuesInAFileExpectedException() {
    String inputFile = "src/test/resources/fileWithTemplateWithWrongNumberOfValues.txt";
    System.out.println("-------------------------");
    System.out.println("start of shouldBeAllValuesInAFileExpectedException with input: " + inputFile);
    ReadFromFile readFromFile = new ReadFromFile(inputFile);
    readFromFile.readTemplateFromFile();

    Throwable thrown = assertThrows(InvalidNumberOfArgumentsException.class, () -> readFromFile.readValuesFromFile());
    assertEquals("Invalid number of params", thrown.getMessage());
    System.out.println("end of shouldBeAllValuesInAFileExpectedException");
    System.out.println("-------------------------");
  }

  //Implement Disabling test on condition
  @Test
  @DisabledIfEnvironmentVariable(named = "OS", matches = "Windows.*")
  void shouldBeReadLatinFromFile() {
    System.out.println("-------------------------");
    System.out.println("start of shouldBeReadLatinFromFile");
    String inputFile = "src/test/resources/fileWithLatin.txt";

    ReadFromFile readFromFile = new ReadFromFile(inputFile);
    TemplateFilling template = readFromFile.readTemplateFromFile();
    String message = "This is a templateÑ with values #{val1} and #{val2}";

    System.out.println("Latin Template: " + template.getTemplate());

    Assertions.assertThat(template.getTemplate())
              .isEqualTo(new String(message.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
    System.out.println("end of shouldBeAllValuesInAFile");
    System.out.println("-------------------------");
  }
}
