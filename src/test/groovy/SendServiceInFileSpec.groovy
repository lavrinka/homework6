import org.epam.service.SendServiceInFile

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class SendServiceInFileSpec extends spock.lang.Specification {

    public void "send message from file to file"() {
        given: "send service"
        SendServiceInFile service = new SendServiceInFile();

        and: "input and output files"
        String inputFile = "src/test/resources/fileWithLatin.txt"
        String outputFile = "src/test/resources/fileWithLatin_output_spock.txt"

        when: "we read values we get error"
        service.sendMessage(inputFile, outputFile)

        then: "the file with message should exist"
        Path outputFilePath = Paths.get(outputFile)
        Files.exists(outputFilePath)

        and: "the file should contain one line"
        List<String> read = Files.readAllLines(outputFilePath)
        read.size() == 1

        and: "message should equal to Latin message"
        read.get(0) == "This is a templateÑ with values #{value} and 123Ñ"
    }

}

