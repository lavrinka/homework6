import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.epam.write.WriteToFile;
import org.junit.jupiter.api.Test;

public class WriteToFileTest {

  @Test
  void shouldUseLatinForWrite() {
    String outputFile = "src/test/resources/fileoutput_shouldUseLatinForWrite.txt";

    String message = new String("This is a template with values #{value} and 123Ñ".getBytes(StandardCharsets.UTF_8),
        StandardCharsets.ISO_8859_1);
    WriteToFile writeToFile = new WriteToFile(outputFile);
    writeToFile.writeMessageToFile(message);

    try {
      Path outputPath = Paths.get("src/test/resources/fileoutput_shouldUseLatinForWrite.txt");
      List<String> read = Files.readAllLines(outputPath);
      Assertions.assertThat(read.size())
                .isEqualTo(1);
      Assertions.assertThat(read.get(0))
                .isEqualTo("This is a template with values #{value} and 123Ñ");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
