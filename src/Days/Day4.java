package Days;

import utility.FileReader;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4 {

    public Day4() {
        String text = new FileReader("resources/D4/input").fileReaderString();
        System.out.println("D4 - Your answer is: " + genPass(text, "00000"));
        System.out.println("D4/2 - Your second answer is: " + genPass(text, "000000"));
    }

    static int genPass(String text, String match) {
        int answer = 0;
        while (!hashMD5(text + answer).startsWith(match)) {
            answer++;
        }
        return answer;
    }

    static String hashMD5(String text) {
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
