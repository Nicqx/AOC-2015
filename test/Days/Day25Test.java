package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import java.util.ArrayList;

import static Days.Day25.calculateCode;
import static Days.Day25.processFileContent;


@DisplayName("Testing the Day25 class")
public class Day25Test {
    @ParameterizedTest
    @ValueSource(strings = {"input:19980801"})
    void checkOriginalInput(String files) {
        String fileContent = new FileReader("resources/D25/" + files.split(":")[0]).fileReaderString();
        long start = 20151125;
        int[] ints = processFileContent(fileContent);
        Assert.assertEquals(calculateCode(start, ints[0], ints[1]), Double.parseDouble(files.split(":")[1]), "Expected result is not correct");
    }
}
