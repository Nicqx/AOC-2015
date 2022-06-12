package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {
    public Day1() {
        try (Scanner scanner = new Scanner(new File("resources/D1/input"))) {
            while (scanner.hasNext()) {
                System.out.println("D1 - Santa goes to floor: " + process(scanner.nextLine()));
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    private static int process(String text) {
        int result = 0;
        for (int i = 0; i < text.length(); i++)
            if (text.charAt(i) == '(') {
                result++;
            } else if (text.charAt(i) == ')') {
                result--;
            }

        return result;
    }
}
