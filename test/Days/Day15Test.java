package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import java.util.ArrayList;

import static Days.Day15.*;


@DisplayName("Testing the Day15 class")
public class Day15Test {
    @ParameterizedTest
    @ValueSource(strings = {"input1:62842880"})
    void checkFirstPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D15/" + files.split(":")[0]).fileReaderArrayList();
        ArrayList<Day15.Ingredients> ingredientsList = new ArrayList<>();
        ArrayList<int[]> combinations = combinations();
        processFileContent(fileContent, ingredientsList);
        Assert.assertEquals(calculateHighestScoring(combinations, ingredientsList), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input1:57600000"})
    void checkSecondPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D15/" + files.split(":")[0]).fileReaderArrayList();
        ArrayList<Day15.Ingredients> ingredientsList = new ArrayList<>();
        ArrayList<int[]> combinations = combinations();
        processFileContent(fileContent, ingredientsList);
        Assert.assertEquals(calculateHighestScoringWithCalorieLimits(combinations, ingredientsList), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input:18965440:15862900"})
    void checkOriginalInput(String files) {
        ArrayList<String> fileContent = new FileReader("resources/D15/" + files.split(":")[0]).fileReaderArrayList();
        ArrayList<Day15.Ingredients> ingredientsList = new ArrayList<>();
        ArrayList<int[]> combinations = combinations();
        processFileContent(fileContent, ingredientsList);
        Assert.assertEquals(calculateHighestScoring(combinations, ingredientsList), Integer.parseInt(files.split(":")[1]), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        Assert.assertEquals(calculateHighestScoringWithCalorieLimits(combinations, ingredientsList), Integer.parseInt(files.split(":")[2]), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }
}
