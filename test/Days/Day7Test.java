package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Days.Day5.counter;
import static Days.Day7.processFileContent;

@DisplayName("Testing the Day7 class")
public class Day7Test {
    @ParameterizedTest
    @ValueSource(strings = {"input1"})
    void checkFirstPart(String files) {
        Map<String, String> nodes = new HashMap<>();
        ArrayList<String> fileContent = new FileReader("test-resources/D7/" + files).fileReaderArrayList();
        processFileContent(fileContent,null, nodes);
        Assert.assertEquals(Integer.parseInt(nodes.get("e")), 507, "Expected result is not correct");
        Assert.assertEquals(Integer.parseInt(nodes.get("f")), 492, "Expected result is not correct");
        Assert.assertEquals(Integer.parseInt(nodes.get("g")), 114, "Expected result is not correct");
        Assert.assertEquals(Integer.parseInt(nodes.get("h")), 65412, "Expected result is not correct");
        Assert.assertEquals(Integer.parseInt(nodes.get("i")), 65079, "Expected result is not correct");
        Assert.assertEquals(Integer.parseInt(nodes.get("x")), 123, "Expected result is not correct");
        Assert.assertEquals(Integer.parseInt(nodes.get("y")), 456, "Expected result is not correct");
    }
    @ParameterizedTest
    @ValueSource(strings = {"input:16076:2797"})
    void checkOriginalInput(String files) {
        Map<String, String> nodes = new HashMap<>();
        ArrayList<String> fileContent = new FileReader("resources/D7/" + files.split(":")[0]).fileReaderArrayList();
        processFileContent(fileContent,null, nodes);
        Assert.assertEquals(Integer.parseInt(nodes.get("a")), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        String bOutput = nodes.get("a");
        nodes.clear();
        processFileContent(fileContent, bOutput, nodes);
        Assert.assertEquals(Integer.parseInt(nodes.get("a")), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }
}