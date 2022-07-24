package Days;

import utility.FileReader;

public class Day1 {

    public Day1() {
        String text = new FileReader("resources/D1/input").fileReaderString();
        System.out.println("D1 - Santa goes to floor: " + findLastPosition(text));
        System.out.println("D1/2 - Santa enters to the basement at: " + findFirstBasementCoord(text));
    }

    static int findLastPosition(String text) {
        int result = 0;
        for (int i = 0; i < text.length(); i++) {
            switch (text.charAt(i)) {
                case '(' -> result++;
                case ')' -> result--;
            }
        }
        return result;
    }

    static int findFirstBasementCoord(String text) {
        int result = 0;
        int coord = 0;
        for (int i = 0; i < text.length(); i++) {
            switch (text.charAt(i)) {
                case '(' -> result++;
                case ')' -> result--;
            }
            coord = i + 1;
            if (result < 0) {
                break;
            }
        }
        return coord;
    }
}
