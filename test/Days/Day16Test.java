package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Days.Day16.*;

@DisplayName("Testing the Day16 class")
public class Day16Test {
    @ParameterizedTest
    @ValueSource(strings = {"input:103:405"})
    void checkOriginalInput(String files) {
        ArrayList<String> fileContent = new FileReader("resources/D16/" + files.split(":")[0]).fileReaderArrayList();
        Map<Integer, Map<String, Integer>> knowledge = new HashMap<>();
        Map<String, Integer> clues = new HashMap<>();
        createClues(clues);
        processFileContent(knowledge, fileContent);
        Assert.assertEquals(exactMach(knowledge, clues), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        Assert.assertEquals(rangeMach(knowledge, clues), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }
}
