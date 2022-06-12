package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day5 {
    public Day5() {
        try (Scanner scanner = new Scanner(new File("resources/D5/input"))) {
            int counter_old = 0;
            int counter_new = 0;
            while (scanner.hasNext()) {
                String text = scanner.nextLine();
                if (process_old(text)) {
                    counter_old++;
                }
                if (process_new(text)){
                    counter_new++;
                }
            }
            System.out.println("D5 - The counter of nice strings with old rule is: " + counter_old);
            System.out.println("D5/2 - The counter of nice strings with new rule is: " + counter_new);

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    private static boolean process_new(String text){
        return (repeatingPairsWOOverlapping(text) && repeatBetweenOne(text));
    }

    private static boolean process_old(String text) {
        return (restrictedGroups(text) && doubleLetter(text) && atLeastThreeVowel(text));
    }

    private static boolean repeatingPairsWOOverlapping(String text){
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

    private static boolean repeatBetweenOne(String text){
        boolean res = false;
        for (int i = 0; i < text.length() - 2; i++){
            if (text.charAt(i) == text.charAt(i + 2)) {
                res = true;
                break;
            }
        }
        return res;
    }

    private static boolean restrictedGroups(String text) {
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


    private static boolean doubleLetter(String text) {
        boolean result = false;
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) == text.charAt(i - 1)) {
                result = true;
                break;
            }
        }

        return result;
    }

    private static boolean atLeastThreeVowel(String text) {
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
