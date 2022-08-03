package Days;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import java.util.ArrayList;

import static Days.Day24.*;

@DisplayName("Testing the Day24 class")
public class Day24Test {
    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {"input1:99"})
    void checkFirstPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D24/" + files.split(":")[0]).fileReaderArrayList();
        ArrayList<Integer> numbers = processFileContent(fileContent);
        ArrayList<ArrayList<Integer>> variations = new ArrayList<>();
        generateVariations(3, variations, numbers);
        Assert.assertEquals(minimalQEinMinimalPiece(variations), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {"input:11266889531:77387711"})
    void checkOriginalInput(String files) {
        ArrayList<String> fileContent = new FileReader("resources/D24/" + files.split(":")[0]).fileReaderArrayList();
        ArrayList<Integer> numbers = processFileContent(fileContent);
        ArrayList<ArrayList<Integer>> variations = new ArrayList<>();
        generateVariations(3, variations, numbers);
        Assert.assertEquals(minimalQEinMinimalPiece(variations), Double.parseDouble(files.split(":")[1]), "Expected result is not correct");
        generateVariations(4, variations, numbers);
        Assert.assertEquals(minimalQEinMinimalPiece(variations), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }
}
