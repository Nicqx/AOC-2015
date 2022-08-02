package Days;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import java.util.HashMap;
import java.util.Map;

import static Days.Day20.processFirstPart;
import static Days.Day20.processSecondPart;

@DisplayName("Testing the Day20 class")
public class Day20Test {
    static Map<Integer, Integer> globalDividers = new HashMap<>();

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {"input:665280:705600"})
    void checkOriginalInput(String files) {
        String text = new FileReader("resources/D20/" + files.split(":")[0]).fileReaderString();

        Assert.assertEquals(processFirstPart(Integer.parseInt(text)), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        Assert.assertEquals(processSecondPart(Integer.parseInt(text), globalDividers), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }
}
