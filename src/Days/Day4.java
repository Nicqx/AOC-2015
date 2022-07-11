package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Day4 {

    public Day4() {
        String text = fileReader("resources/D4/input");
        System.out.println("D4 - Your answer is: " + genPass(text, "00000"));
        System.out.println("D4/2 - Your second answer is: " + genPass(text, "000000"));
    }

    private String fileReader(String res) {
        String result = "";
        try (Scanner scanner = new Scanner(new File(res))) {
            while (scanner.hasNext()) {
                result = scanner.nextLine();
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        return result;
    }

    private static int genPass(String text, String match) {
        int answer = 0;
        while (!hashMD5(text + answer).startsWith(match)) {
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
