package Days;

import utility.FileReader;

public class Day1 {
    private static int coord = 0;

    public Day1() {
        String text = new FileReader("resources/D1/input").fileReaderString();
        System.out.println("D1 - Santa goes to floor: " + process(text));
        System.out.println("D1/2 - Santa enters to the basement at: " + coord);
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
