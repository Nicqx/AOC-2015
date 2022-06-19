package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day8 {
    ArrayList<String> fullString = new ArrayList<>();
    ArrayList<Integer> charString = new ArrayList<>();
    ArrayList<Integer> newCharString = new ArrayList<>();

    public Day8() {
        try (Scanner scanner = new Scanner(new File("resources/D8/input"))) {
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                fullString.add(s);
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        createCharString();
        System.out.println("D8 - the needed amount of memory " + counterVal());
        createNewFullString();
        System.out.println("D8/2 - the needed amount of memory " + counterVal2());

    }

    private int counterVal() {
        int sum = 0;
        for (int i = 0; i < fullString.size(); i++) {
            sum += fullString.get(i).length() - charString.get(i);
        }
        return sum;
    }

    private int counterVal2() {
        int sum = 0;
        for (int i = 0; i < fullString.size(); i++) {
            sum += newCharString.get(i) - fullString.get(i).length();
        }
        return sum;
    }

    private void createCharString() {
        for (String val : fullString) {
            int counter = 0;
            val = val.substring(1, val.length() - 1);
            Matcher matcher = Pattern.compile("(\\\\[Xx][\\da-fA-F]{2})").matcher(val);
            while (matcher.find()) {
                counter++;
                val = val.replace(matcher.group(1), "");
            }
            matcher = Pattern.compile("(\\\\[\"\\\\])").matcher(val);
            while (matcher.find()) {
                counter++;
                val = val.replace(matcher.group(1), "");
            }
            charString.add(counter + val.length());
        }
    }

    private void createNewFullString() {
        for (String val : fullString) {
            int counter = 6;
            val = val.substring(1, val.length() - 1);
            Matcher matcher = Pattern.compile("(\\\\[\"\\\\])").matcher(val);
            while (matcher.find()) {
                counter += 4;
                val = val.replace(matcher.group(1), "");
            }
            matcher = Pattern.compile("(\\\\)").matcher(val);
            while (matcher.find()) {
                counter += 2;
                val = val.replace(matcher.group(1), "");
            }
            newCharString.add(counter + val.length());
        }
    }
}
