package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import java.util.ArrayList;

import static Days.Day14.*;

@DisplayName("Testing the Day14 class")
public class Day14Test {
    @ParameterizedTest
    @ValueSource(strings = {"input1:1120"})
    void checkFirstPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D14/" + files.split(":")[0]).fileReaderArrayList();
        ArrayList<Day14.Reindeer> fleet = new ArrayList<>();
        processFileContent(fileContent, fleet);
        Assert.assertEquals(maxDistance(1000, fleet), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input1:689"})
    void checkSecondPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D14/" + files.split(":")[0]).fileReaderArrayList();
        ArrayList<Day14.Reindeer> fleet = new ArrayList<>();
        processFileContent(fileContent, fleet);
        Assert.assertEquals(maxPoints(1000, fleet), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input:2655:1059"})
    void checkOriginalInput(String files) {
        ArrayList<String> fileContent = new FileReader("resources/D14/" + files.split(":")[0]).fileReaderArrayList();
        ArrayList<Day14.Reindeer> fleet = new ArrayList<>();
        processFileContent(fileContent, fleet);
        Assert.assertEquals(maxDistance(2503, fleet), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        Assert.assertEquals(maxPoints(2503, fleet), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }
}
