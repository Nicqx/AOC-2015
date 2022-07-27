package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import java.util.ArrayList;

import static Days.Day17.*;


@DisplayName("Testing the Day17 class")
public class Day17Test {
    @ParameterizedTest
    @ValueSource(strings = {"input1:4"})
    void checkFirstPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D17/" + files.split(":")[0]).fileReaderArrayList();
        final int ALLAMOUNT = 25;
        ArrayList<Integer> containers = new ArrayList<>();
        ArrayList<ArrayList<Integer>> variations = new ArrayList<>();
        processFileContent(fileContent, containers);
        generateVariations(variations, containers);
        variations = getValidVariations(variations, ALLAMOUNT);
        Assert.assertEquals(variations.size(), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input1:3"})
    void checkSecondPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D17/" + files.split(":")[0]).fileReaderArrayList();
        final int ALLAMOUNT = 25;
        ArrayList<Integer> containers = new ArrayList<>();
        ArrayList<ArrayList<Integer>> variations = new ArrayList<>();
        processFileContent(fileContent, containers);
        generateVariations(variations, containers);
        variations = getValidVariations(variations, ALLAMOUNT);
        Assert.assertEquals(getMinimalValidVariations(variations), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input:4372:4"})
    void checkOriginalInput(String files) {
        ArrayList<String> fileContent = new FileReader("resources/D17/" + files.split(":")[0]).fileReaderArrayList();
        final int ALLAMOUNT = 150;
        ArrayList<Integer> containers = new ArrayList<>();
        ArrayList<ArrayList<Integer>> variations = new ArrayList<>();
        processFileContent(fileContent, containers);
        generateVariations(variations, containers);
        variations = getValidVariations(variations, ALLAMOUNT);
        Assert.assertEquals(variations.size(), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        Assert.assertEquals(getMinimalValidVariations(variations), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }
}
