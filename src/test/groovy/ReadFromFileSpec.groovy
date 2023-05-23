import org.epam.exception.InvalidNumberOfArgumentsException
import org.epam.read.ReadFromFile

import static org.junit.jupiter.api.Assertions.assertThrows

public class ReadFromFileSpec extends spock.lang.Specification{

    public void "there should be all values in a file"() {
        given: "an input file and creation of ReadFromFile"
        String inputFile = "src/test/resources/fileWithTemplateWithWrongNumberOfValues.txt"
        ReadFromFile readFromFile = new ReadFromFile(inputFile)

        and: "read template from the file"
        readFromFile.readTemplateFromFile()

        when: "we read values we get error"
        Throwable thrown = assertThrows(InvalidNumberOfArgumentsException.class, () -> readFromFile.readValuesFromFile())

        then: "compare error with error message for InvalidNumberOfArgumentsException"
        thrown.getMessage() == "Invalid number of params"
    }

}
