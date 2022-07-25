package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import java.util.ArrayList;

import static Days.Day6.*;

@DisplayName("Testing the Day6 class")
public class Day6Test {
    @ParameterizedTest
    @ValueSource(strings = {"input1:1000000", "input2:1000", "input3:0"})
    void checkFirstPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D6/" + files.split(":")[0]).fileReaderArrayList();
        init();
        processFileContent(fileContent);
        Assert.assertEquals(readOut(), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input4:1", "input5:2000000"})
    void checkSecondPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D6/" + files.split(":")[0]).fileReaderArrayList();
        init_new();
        processFileContent(fileContent);
        Assert.assertEquals(readOut_new(), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input:569999:17836115"})
    void checkOriginalInput(String files) {
        ArrayList<String> fileContent = new FileReader("resources/D6/" + files.split(":")[0]).fileReaderArrayList();
        init();
        init_new();
        processFileContent(fileContent);
        Assert.assertEquals(readOut(), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        Assert.assertEquals(readOut_new(), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }
}
