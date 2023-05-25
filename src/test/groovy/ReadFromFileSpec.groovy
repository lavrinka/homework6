import org.epam.exception.InvalidNumberOfArgumentsException
import org.epam.read.ReadFromFile

public class ReadFromFileSpec extends spock.lang.Specification{

    public void "there should be all values in a file"() {
        given: "an input file and creation of ReadFromFile"
        String inputFile = "src/test/resources/fileWithTemplateWithWrongNumberOfValues.txt"
        ReadFromFile readFromFile = new ReadFromFile(inputFile)

        and: "read template from the file"
        readFromFile.readTemplateFromFile()

        when: "we read values we get error"
        readFromFile.readValuesFromFile()

        then: "InvalidNumberOfArgumentsException exception thrown"
        InvalidNumberOfArgumentsException exception = thrown()

        and: "compare error with error message for InvalidNumberOfArgumentsException"
        exception.getMessage() == "Invalid number of params"
    }

}
