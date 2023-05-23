import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class CustomWriteToFileExtention implements BeforeAllCallback, AfterAllCallback {

  private final FileOutputStream outFile;

  public CustomWriteToFileExtention() throws FileNotFoundException {
    this.outFile = new FileOutputStream("result.txt");
  }

  @Override
  public void afterAll(ExtensionContext extensionContext) throws Exception {
    System.setOut(System.out);
    System.setErr(System.err);
    outFile.close();
  }

  @Override
  public void beforeAll(ExtensionContext extensionContext) {
    PrintStream newPrintStream = new PrintStream(outFile);
    System.setOut(newPrintStream);
    System.setErr(newPrintStream);
  }
}
