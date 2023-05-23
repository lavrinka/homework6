import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.epam.write.WriteToFile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class WriteToFileTest {

  @Test
  void shouldUseLatinForWrite(@TempDir Path tempDir) {
    Path outputFilePath = tempDir.resolve("fileoutput_shouldUseLatinForWrite.txt");
    String message = new String("This is a template with values #{value} and 123Ñ".getBytes(StandardCharsets.UTF_8),
        StandardCharsets.ISO_8859_1);
    WriteToFile writeToFile = new WriteToFile(outputFilePath.toString());
    writeToFile.writeMessageToFile(message);

    try {
      assertTrue(Files.exists(outputFilePath));
      List<String> read = Files.readAllLines(outputFilePath);
      Assertions.assertThat(read.size())
                .isEqualTo(1);
      Assertions.assertThat(read.get(0))
                .isEqualTo("This is a template with values #{value} and 123Ñ");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
