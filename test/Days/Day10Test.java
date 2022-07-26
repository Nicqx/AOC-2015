package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import static Days.Day10.process;

@DisplayName("Testing the Day10 class")
public class Day10Test {
    @ParameterizedTest
    @ValueSource(strings = {"input1"})
    void checkFirstPart(String files) {
        String inputFile = "test-resources/D10/" + files;
        String text = new FileReader(inputFile).fileReaderString();
        Assert.assertEquals(process(text, 1).length(), 2, "Expected result is not correct");
        Assert.assertEquals(process(text, 2).length(), 2, "Expected result is not correct");
        Assert.assertEquals(process(text, 3).length(), 4, "Expected result is not correct");
        Assert.assertEquals(process(text, 4).length(), 6, "Expected result is not correct");
        Assert.assertEquals(process(text, 5).length(), 6, "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input:360154:5103798"})
    void checkOriginalInput(String files) {
        String inputFile = "resources/D10/" + files.split(":")[0];
        String text = new FileReader(inputFile).fileReaderString();
        Assert.assertEquals(process(text, 40).length(), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        Assert.assertEquals(process(text, 50).length(), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }
}
