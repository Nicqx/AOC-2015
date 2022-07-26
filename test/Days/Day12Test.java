package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import static Days.Day12.omitRed;
import static Days.Day12.sumNum;

@DisplayName("Testing the Day12 class")
public class Day12Test {
    @ParameterizedTest
    @ValueSource(strings = {"input1:6", "input2:6", "input3:3", "input4:3", "input5:0", "input6:0", "input7:0", "input8:0"})
    void checkFirstPart(String files) {
        String inputFile = "test-resources/D12/" + files.split(":")[0];
        String text = new FileReader(inputFile).fileReaderString();
        Assert.assertEquals(sumNum(text), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input1:6", "input9:4", "input10:0", "input11:6"})
    void checkSecondPart(String files) {
        String inputFile = "test-resources/D12/" + files.split(":")[0];
        String text = new FileReader(inputFile).fileReaderString();
        Assert.assertEquals(sumNum(omitRed(text)), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input:111754:65402"})
    void checkOriginalInput(String files) {
        String inputFile = "resources/D12/" + files.split(":")[0];
        String text = new FileReader(inputFile).fileReaderString();
        Assert.assertEquals(sumNum(text), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        Assert.assertEquals(sumNum(omitRed(text)), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }
}
