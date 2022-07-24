package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import java.util.ArrayList;

import static Days.Day2.*;

@DisplayName("Testing the Day2 class")
class Day2Test {

    @ParameterizedTest
    @ValueSource(strings = {"input1:58", "input2:43"})
    void checkFirstPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D2/" + files.split(":")[0]).fileReaderArrayList();
        processFileContent(fileContent);
        Assert.assertEquals(processWrap(), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input1:34", "input2:14"})
    void checkSecondPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D2/" + files.split(":")[0]).fileReaderArrayList();
        processFileContent(fileContent);
        Assert.assertEquals(processRibbon(), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input:1586300:3737498"})
    void checkOriginalInput(String files) {
        ArrayList<String> fileContent = new FileReader("resources/D2/" + files.split(":")[0]).fileReaderArrayList();
        processFileContent(fileContent);
        Assert.assertEquals(processWrap(), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        Assert.assertEquals(processRibbon(), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }

}