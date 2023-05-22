package org.epam.write;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteToFile {

  private final Path filePath;

  public WriteToFile(String file) {
    this.filePath = Paths.get(file);
  }
  public void writeMessageToFile(String message){
    try {
      byte[] strToBytes = message.getBytes(StandardCharsets.ISO_8859_1);

      Files.write(filePath, strToBytes);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
