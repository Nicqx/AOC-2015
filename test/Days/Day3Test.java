package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import static Days.Day3.processRoute;
import static Days.Day3.processRouteWithRoboSanta;

@DisplayName("Testing the Day3 class")
class Day3Test {
    @ParameterizedTest
    @ValueSource(strings = {"input1:2", "input2:4", "input3:2"})
    void checkFirstPart(String files) {
        String text = new FileReader("test-resources/D3/" + files.split(":")[0]).fileReaderString();
        Assert.assertEquals(processRoute(text), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input4:3", "input2:3", "input3:11"})
    void checkSecondPart(String files) {
        String text = new FileReader("test-resources/D3/" + files.split(":")[0]).fileReaderString();
        Assert.assertEquals(processRouteWithRoboSanta(text), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input:2081:2341"})
    void checkOriginalInput(String files) {
        String text = new FileReader("resources/D3/" + files.split(":")[0]).fileReaderString();
        Assert.assertEquals(processRoute(text), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        Assert.assertEquals(processRouteWithRoboSanta(text), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }

}