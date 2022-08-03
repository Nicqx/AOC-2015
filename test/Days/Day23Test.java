package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import java.util.ArrayList;

import static Days.Day23.*;

@DisplayName("Testing the Day23 class")
public class Day23Test {
    @ParameterizedTest
    @ValueSource(strings = {"input1:2"})
    void checkFirstPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D23/" + files.split(":")[0]).fileReaderArrayList();
        Registers registers = calculateB(0, fileContent);

        Assert.assertEquals(registers.getA(), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
    }
    @ParameterizedTest
    @ValueSource(strings = {"input:307:160"})
    void checkOriginalInput(String files) {
        ArrayList<String> fileContent = new FileReader("resources/D23/" + files.split(":")[0]).fileReaderArrayList();
        Registers registers = calculateB(0, fileContent);
        Assert.assertEquals(registers.getB(), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        registers = calculateB(1, fileContent);
        Assert.assertEquals(registers.getB(), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }
}
