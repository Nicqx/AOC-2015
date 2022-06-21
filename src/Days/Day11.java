package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Day11 {
    String password = "";

    public Day11() {
        try (Scanner scanner = new Scanner(new File("resources/D11/input"))) {
            while (scanner.hasNext()) {
                password = scanner.nextLine();
            }
            do {
                password = increment(password, password.length() - 1);
            } while (validate(password));
            System.out.println("D11 - the next valid password is: " + password);
            do {
                password = increment(password, password.length() - 1);
            } while (validate(password));
            System.out.println("D11/2 - the next valid password is: " + password);

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    private boolean validate(String password) {
        return checkProhibitedLetters(password) || checkThreeIncreasingLetter(password) || checkTwoDifferentOverlappingPairs(password);
    }

    private boolean checkThreeIncreasingLetter(String password) {
        byte[] charArray = password.getBytes(StandardCharsets.UTF_8);
        for (int i = 2; i < charArray.length; i++) {
            if ((charArray[i] == charArray[i - 1] + 1) && (charArray[i - 1] == charArray[i - 2] + 1)) {
                return false;
            }
        }

        return true;
    }

    private boolean checkTwoDifferentOverlappingPairs(String password) {
        byte[] charArray = password.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < charArray.length - 1; i++) {
            if (charArray[i] == charArray[i + 1]) {
                for (int j = i + 2; j < charArray.length - 1; j++) {
                    if (charArray[j] == charArray[j + 1]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean checkProhibitedLetters(String password) {
        byte[] charArray = password.getBytes(StandardCharsets.UTF_8);
        for (byte b : charArray) {
            if ((b == 105) || (b == 108) || (b == 111)) {
                return true;
            }
        }
        return false;
    }

    private String increment(String password, int poz) {
        byte[] chararray = password.getBytes(StandardCharsets.UTF_8);
        if (chararray[poz] == 122) {
            chararray[poz] = (byte) (97);
            if (poz != 0) {
                chararray = increment(new String(chararray, StandardCharsets.UTF_8), poz - 1).getBytes(StandardCharsets.UTF_8);
            }

        } else {
            chararray[poz] = (byte) ((chararray[poz]) + 1);
        }
        return new String(chararray, StandardCharsets.UTF_8);
    }

}

