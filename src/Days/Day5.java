package Days;

import utility.FileReader;

import java.util.ArrayList;

public class Day5 {
    ArrayList<String> fileContent = new FileReader("resources/D5/input").fileReaderArrayList();

    public Day5() {
        System.out.println("D5 - The counter of nice strings with old rule is: " + counter(fileContent, true));
        System.out.println("D5/2 - The counter of nice strings with new rule is: " + counter(fileContent, false));
    }

    static int counter(ArrayList<String> fileContent, boolean old_method) {
        int counter = 0;
        for (String line : fileContent) {
            if (old_method && process_old(line)) {
                counter++;
            } else if (!old_method && process_new(line)) {
                counter++;
            }
        }
        return counter;
    }

    static boolean process_new(String text) {
        return (repeatingPairsWOOverlapping(text) && repeatBetweenOne(text));
    }

    static boolean process_old(String text) {
        return (restrictedGroups(text) && doubleLetter(text) && atLeastThreeVowel(text));
    }

    static boolean repeatingPairsWOOverlapping(String text) {
        char movingA = text.charAt(0);
        char movingB = text.charAt(1);
        boolean res = false;
        for (int j = 0; j < text.length() - 2; j++) {
            for (int i = j + 2; i < text.length() - 1; i++) {
                if ((text.charAt(i) == movingA) && (text.charAt(i + 1) == movingB)) {
                    res = true;
                    break;
                }
            }
            movingA = text.charAt(j + 1);
            movingB = text.charAt(j + 2);
        }
        return res;
    }

    static boolean repeatBetweenOne(String text) {
        boolean res = false;
        for (int i = 0; i < text.length() - 2; i++) {
            if (text.charAt(i) == text.charAt(i + 2)) {
                res = true;
                break;
            }
        }
        return res;
    }

    static boolean restrictedGroups(String text) {
        boolean result = true;
        for (int i = 1; i < text.length(); i++) {
            if ((text.charAt(i - 1) == 'a' && text.charAt(i) == 'b') ||
                    (text.charAt(i - 1) == 'c' && text.charAt(i) == 'd') ||
                    (text.charAt(i - 1) == 'p' && text.charAt(i) == 'q') ||
                    (text.charAt(i - 1) == 'x' && text.charAt(i) == 'y')) {
                result = false;
                break;
            }
        }
        return result;
    }


    static boolean doubleLetter(String text) {
        boolean result = false;
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) == text.charAt(i - 1)) {
                result = true;
                break;
            }
        }

        return result;
    }

    static boolean atLeastThreeVowel(String text) {
        int counter = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == 'a' ||
                    text.charAt(i) == 'e' ||
                    text.charAt(i) == 'i' ||
                    text.charAt(i) == 'o' ||
                    text.charAt(i) == 'u') {
                counter++;
            }
        }
        return (counter >= 3);
    }
}
