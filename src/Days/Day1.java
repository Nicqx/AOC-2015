package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {
    private static int coord = 0;

    public Day1() {
        System.out.println("D1 - Santa goes to floor: " + process(fileReader("resources/D1/input")));
        System.out.println("D1/2 - Santa enters to the basement at: " + coord);
    }

    private String fileReader(String res) {
        String text = "";
        try (Scanner scanner = new Scanner(new File(res))) {
            while (scanner.hasNext()) {
                text = scanner.nextLine();
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        return text;
    }

    private static int process(String text) {
        int result = 0;
        for (int i = 0; i < text.length(); i++) {
            switch (text.charAt(i)) {
                case '(' -> result++;
                case ')' -> result--;
            }
            if (coord == 0 && result < 0) {
                coord = i + 1;
            }
        }

        return result;
    }
}
