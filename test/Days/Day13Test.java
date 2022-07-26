package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static Days.Day13.*;

@DisplayName("Testing the Day13 class")
public class Day13Test {
    @ParameterizedTest
    @ValueSource(strings = {"input1:330"})
    void checkFirstPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D13/" + files.split(":")[0]).fileReaderArrayList();
        ArrayList<Day13.Happiness> happinessMatrix = new ArrayList<>();
        Set<String> namesSet = new HashSet<>();
        ArrayList<String> namesPermutationList = new ArrayList<>();
        processFileContent(fileContent, happinessMatrix);
        Assert.assertEquals(calcMaxHappiness(namesSet, namesPermutationList, happinessMatrix), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input:709:668"})
    void checkOriginalInput(String files) {
        ArrayList<String> fileContent = new FileReader("resources/D13/" + files.split(":")[0]).fileReaderArrayList();
        ArrayList<Day13.Happiness> happinessMatrix = new ArrayList<>();
        Set<String> namesSet = new HashSet<>();
        ArrayList<String> namesPermutationList = new ArrayList<>();
        processFileContent(fileContent, happinessMatrix);
        Assert.assertEquals(calcMaxHappiness(namesSet, namesPermutationList, happinessMatrix), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        namesPermutationList.clear();
        addMyself(namesSet, happinessMatrix);
        Assert.assertEquals(calcMaxHappiness(namesSet, namesPermutationList, happinessMatrix), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }
}
