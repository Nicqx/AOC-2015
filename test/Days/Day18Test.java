package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Days.Day18.*;


@DisplayName("Testing the Day18 class")
public class Day18Test {
    @ParameterizedTest
    @ValueSource(strings = {"input:814:924"})
    void checkOriginalInput(String files) {
        ArrayList<String> fileContent = new FileReader("resources/D18/" + files.split(":")[0]).fileReaderArrayList();
        boolean[][] grid = new boolean[100][100];
        final int ROUNDS = 100;
        processFileContent(fileContent, grid);
        process(false, grid, ROUNDS);
        Assert.assertEquals(countLightsOn(grid), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        processFileContent(fileContent, grid);
        process(true, grid, ROUNDS);
        Assert.assertEquals(countLightsOn(grid), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }
}
