package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import java.util.ArrayList;

import static Days.Day5.counter;

@DisplayName("Testing the Day5 class")
public class Day5Test {
    @ParameterizedTest
    @ValueSource(strings = {"input1:1", "input2:0", "input3:0", "input4:0", "input5:1"})
    void checkFirstPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D5/" + files.split(":")[0]).fileReaderArrayList();
        Assert.assertEquals(counter(fileContent, true), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input6:1", "input7:1", "input8:0", "input9:0"})
    void checkSecondPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D5/" + files.split(":")[0]).fileReaderArrayList();
        Assert.assertEquals(counter(fileContent, false), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input:255:55"})
    void checkOriginalInput(String files) {
        ArrayList<String> fileContent = new FileReader("resources/D5/" + files.split(":")[0]).fileReaderArrayList();
        Assert.assertEquals(counter(fileContent, true), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        Assert.assertEquals(counter(fileContent, false), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }
}
