package Days;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.Assert;
import utility.FileReader;

import java.util.ArrayList;

import static Days.Day21.*;

@DisplayName("Testing the Day21 class")
public class Day21Test {
    @ParameterizedTest
    @ValueSource(strings = {"input1:true"})
    void checkFirstPart(String files) {
        ArrayList<String> fileContent = new FileReader("test-resources/D21/" + files.split(":")[0]).fileReaderArrayList();
        ProcessedFileContent prfc = processFileContent(fileContent);
        initShop();
        Day21.Character boss = new Day21.Character.CharacterBuilder(prfc.bossHit, prfc.bossDamage, prfc.bossArmor).build();
        Day21.Character player = new Day21.Character.CharacterBuilder(8, 5, 5).build();

        Assert.assertEquals(isPlayerWin(player, boss), Boolean.parseBoolean(files.split(":")[1]), "Expected result is not correct");
    }

    @ParameterizedTest
    @ValueSource(strings = {"input:111:188"})
    void checkOriginalInput(String files) {
        ArrayList<String> fileContent = new FileReader("resources/D21/" + files.split(":")[0]).fileReaderArrayList();
        ProcessedFileContent prfc = processFileContent(fileContent);
        initShop();
        Assert.assertEquals(genFightMinimalCost(prfc.bossHit, prfc.bossDamage, prfc.bossArmor), Integer.parseInt(files.split(":")[1]), "Expected result is not correct");
        Assert.assertEquals(genFightMaximalLosingCost(prfc.bossHit, prfc.bossDamage, prfc.bossArmor), Integer.parseInt(files.split(":")[2]), "Expected result is not correct");
    }
}
