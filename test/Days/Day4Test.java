package Days;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import static Days.Day4.genPass;

@DisplayName("Testing the Day4 class")
public class Day4Test {
    @ParameterizedTest
    @ValueSource(strings = {"input1:609043", "input2:1048970"})
    void checkFirstPart(String files) {
        String text = new FileReader("test-resources/D4/" + files.split(":")[0]).fileReaderString();
        Assert.assertEquals(genPass(text, "00000"), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings ={"input:346386:9958218"})
    void checkOriginalInput(String files){
        String text = new FileReader("resources/D4/" + files.split(":")[0]).fileReaderString();
        Assert.assertEquals(genPass(text, "00000"), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        Assert.assertEquals(genPass(text, "000000"), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }
}
