package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import static Days.Day11.*;

@DisplayName("Testing the Day11 class")
public class Day11Test {
    @Test
    void testFirst() {
        String text = new FileReader("test-resources/D11/input1").fileReaderString();
        Assert.assertFalse(checkThreeIncreasingLetter(text), "Expected result is not correct");
        Assert.assertTrue(checkProhibitedLetters(text), "Expected result is not correct");
    }

    @Test
    void testSecond() {
        String text = new FileReader("test-resources/D11/input2").fileReaderString();
        Assert.assertTrue(checkThreeIncreasingLetter(text), "Expected result is not correct");
        Assert.assertFalse(checkTwoDifferentOverlappingPairs(text), "Expected result is not correct");
    }

    @Test
    void testThird() {
        String text = new FileReader("test-resources/D11/input3").fileReaderString();
        Assert.assertTrue(checkTwoDifferentOverlappingPairs(text), "Expected result is not correct");
    }

    @Test
    void testFourth() {
        String text = new FileReader("test-resources/D11/input4").fileReaderString();
        Assert.assertEquals(findNext(text), "abcdffaa", "Expected result is not correct");
    }

    @Test
    void testFifth() {
        String text = new FileReader("test-resources/D11/input5").fileReaderString();
        Assert.assertEquals(findNext(text), "ghjaabcc", "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input:vzbxxyzz:vzcaabcc"})
    void checkOriginalInput(String files) {
        String text = new FileReader("resources/D11/" + files.split(":")[0]).fileReaderString();
        Assert.assertEquals(findNext(text), files.split(":")[1], "Expected result is not correct");
        Assert.assertEquals(findNext(findNext(text)), files.split(":")[2], "Expected result is not correct");
    }
}
