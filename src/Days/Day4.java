package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Day4 {

    public Day4() {
        try (Scanner scanner = new Scanner(new File("resources/D4/input"))) {
            while (scanner.hasNext()) {
                String text = scanner.nextLine();
                System.out.println("D4 - Your answer is: " + process(text));
                System.out.println("D4/2 - Your second answer is: " + process2(text));
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    private static int process2(String text) {
        int answer = 0;
        while (!hashMD5(text + answer).startsWith("000000")) {
            answer++;
        }
        return answer;
    }

    private static int process(String text) {
        int answer = 0;
        while (!hashMD5(text + answer).startsWith("00000")) {
            answer++;
        }
        return answer;
    }

    private static String hashMD5(String text) {
        byte[] msg = text.getBytes();

        byte[] hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            hash = md.digest(msg);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        StringBuilder strBuilder = new StringBuilder();
        assert hash != null;
        for (byte b : hash) {
            strBuilder.append(String.format("%02x", b));
        }
        return strBuilder.toString();
    }


}
