package Days;

import utility.FileReader;

import java.nio.charset.StandardCharsets;

public class Day11 {
    public Day11() {
        String text = new FileReader("resources/D11/input").fileReaderString();
        String password = findNext(text);

        System.out.println("D11 - the next valid password is: " + password);

        System.out.println("D11/2 - the next valid password is: " + findNext(password));
    }

    private String findNext(String password) {
        do {
            password = increment(password, password.length() - 1);
        } while (validate(password));
        return password;
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
        byte[] charArray = password.getBytes(StandardCharsets.UTF_8);
        if (charArray[poz] == 122) {
            charArray[poz] = (byte) (97);
            if (poz != 0) {
                charArray = increment(new String(charArray, StandardCharsets.UTF_8), poz - 1).getBytes(StandardCharsets.UTF_8);
            }
        } else {
            charArray[poz] = (byte) ((charArray[poz]) + 1);
        }
        return new String(charArray, StandardCharsets.UTF_8);
    }
}

