package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {
    private static int coord = 0;
    private static String text;

    public Day1() {
        try (Scanner scanner = new Scanner(new File("resources/D1/input"))) {
            while (scanner.hasNext()) {
                System.out.println("D1 - Santa goes to floor: " + process(scanner.nextLine()));
                System.out.println("D1/2 - Santa enters to the basement at: " + coord);
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    private static int process(String text) {
        Day1.text = text;
        int result = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '(') {
                result++;
            } else if (text.charAt(i) == ')') {
                result--;
            }
            if (coord == 0 && result < 0) {
                coord = i + 1;
            }
        }

        return result;
    }
}
