import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.epam.ReadingValues;
import org.epam.Value;
import org.junit.jupiter.api.Test;

public class ReadingValuesTest {

  @Test
  void shouldParseValues(){
    ReadingValues readingValues = new ReadingValues();
    String input = "This is template with name #{name}. Insert #{some value}";
    ArrayList<Value> list = new ArrayList<>();
    list.add(new Value("#{name}"));
    list.add(new Value("#{some value}"));
    ArrayList<Value> values = readingValues.readValues(input);
    Assertions.assertThat(values).isNotEmpty().containsAll(list);
  }

}
