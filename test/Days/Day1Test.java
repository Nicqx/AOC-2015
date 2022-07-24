package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import static Days.Day1.findFirstBasementCoord;
import static Days.Day1.findLastPosition;

@DisplayName("Testing the Day1 class")
class Day1Test {
    @ParameterizedTest
    @ValueSource(strings = {"input1:0", "input2:0", "input3:3", "input4:3", "input5:3", "input6:-1", "input7:-1", "input8:-3", "input9:-3"})
    void checkFirstPart(String files) {
        String inputFile = "test-resources/D1/" + files.split(":")[0];
        String text = new FileReader(inputFile).fileReaderString();
        Assert.assertEquals(findLastPosition(text), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }
    @ParameterizedTest
    @ValueSource(strings={"input10:1", "input11:5"})
    void checkSecondPart(String files){
        String inputFile = "test-resources/D1/" + files.split(":")[0];
        String text = new FileReader(inputFile).fileReaderString();
        Assert.assertEquals(findFirstBasementCoord(text), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }
    @ParameterizedTest
    @ValueSource(strings={"input:74:1795"})
    void checkOriginalInput(String files){
        String inputFile = "resources/D1/" + files.split(":")[0];
        String text = new FileReader(inputFile).fileReaderString();
        Assert.assertEquals(findLastPosition(text), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        Assert.assertEquals(findFirstBasementCoord(text), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }
}