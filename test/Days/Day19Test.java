package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import java.util.ArrayList;

import static Days.Day19.*;


@DisplayName("Testing the Day19 class")
public class Day19Test {
    @ParameterizedTest
    @ValueSource(strings = {"input1:4", "input2:7"})
    void checkFirstPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D19/" + files.split(":")[0]).fileReaderArrayList();
        String initial = getInitial(fileContent);
        ArrayList<String[]> replacements = processFileContent(fileContent);
        Assert.assertEquals(doTheReplacements(initial, replacements).size(), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input3:3", "input4:6"})
    void checkSecondPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D19/" + files.split(":")[0]).fileReaderArrayList();
        String initial = getInitial(fileContent);
        ArrayList<String[]> replacements = processFileContent(fileContent);
        Assert.assertEquals(doReverseReplacements(initial, 2, replacements), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input:576:207"})
    void checkOriginalInput(String files) {
        ArrayList<String> fileContent = new FileReader("resources/D19/" + files.split(":")[0]).fileReaderArrayList();
        String initial = getInitial(fileContent);
        ArrayList<String[]> replacements = processFileContent(fileContent);
        Assert.assertEquals(doTheReplacements(initial, replacements).size(), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        Assert.assertEquals(doReverseReplacements(initial, 2, replacements), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }
}
