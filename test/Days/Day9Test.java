package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import java.util.ArrayList;

import static Days.Day9.calculateDistances;
import static Days.Day9.processFileContent;

@DisplayName("Testing the Day9 class")
public class Day9Test {
    @ParameterizedTest
    @ValueSource(strings = {"input1:605"})
    void checkFirstPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D9/" + files.split(":")[0]).fileReaderArrayList();
        ArrayList<Day9.Distances> distanceMatrix = new ArrayList<>();
        processFileContent(fileContent, distanceMatrix);
        Assert.assertEquals(calculateDistances(distanceMatrix, true), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input1:982"})
    void checkSecondPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D9/" + files.split(":")[0]).fileReaderArrayList();
        ArrayList<Day9.Distances> distanceMatrix = new ArrayList<>();
        processFileContent(fileContent, distanceMatrix);
        Assert.assertEquals(calculateDistances(distanceMatrix, false), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input:207:804"})
    void checkOriginalInput(String files) {
        ArrayList<String> fileContent = new FileReader("resources/D9/" + files.split(":")[0]).fileReaderArrayList();
        ArrayList<Day9.Distances> distanceMatrix = new ArrayList<>();
        processFileContent(fileContent, distanceMatrix);
        Assert.assertEquals(calculateDistances(distanceMatrix, true), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        Assert.assertEquals(calculateDistances(distanceMatrix, false), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }
}
