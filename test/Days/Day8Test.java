package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import java.util.ArrayList;

import static Days.Day8.counterVal;
import static Days.Day8.counterValNewRules;


@DisplayName("Testing the Day8 class")
public class Day8Test {
    @ParameterizedTest
    @ValueSource(strings = {"input1:12"})
    void checkFirstPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D8/" + files.split(":")[0]).fileReaderArrayList();
        Assert.assertEquals(counterVal(fileContent), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input1:19"})
    void checkSecondPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D8/" + files.split(":")[0]).fileReaderArrayList();
        Assert.assertEquals(counterValNewRules(fileContent), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input:1333:2046"})
    void checkOriginalInput(String files) {
        ArrayList<String> fileContent = new FileReader("resources/D8/" + files.split(":")[0]).fileReaderArrayList();
        Assert.assertEquals(counterVal(fileContent), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        Assert.assertEquals(counterValNewRules(fileContent), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }
}
