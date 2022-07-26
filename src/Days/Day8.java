package Days;

import utility.FileReader;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day8 {
    ArrayList<String> fileContent = new FileReader("resources/D8/input").fileReaderArrayList();

    public Day8() {
        System.out.println("D8 - the needed amount of memory " + counterVal(fileContent));

        System.out.println("D8/2 - the needed amount of memory with the new rules " + counterValNewRules(fileContent));

    }

    static int counterVal(ArrayList<String> fileContent) {
        ArrayList<String> fullString = new ArrayList<>(fileContent);
        ArrayList<Integer> charString = new ArrayList<>();
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

        int sum = 0;
        for (int i = 0; i < fullString.size(); i++) {
            sum += fullString.get(i).length() - charString.get(i);
        }
        return sum;
    }

    static int counterValNewRules(ArrayList<String> fileContent) {
        ArrayList<String> fullString = new ArrayList<>(fileContent);
        ArrayList<Integer> newCharString = new ArrayList<>();
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

        int sum = 0;
        for (int i = 0; i < fullString.size(); i++) {
            sum += newCharString.get(i) - fullString.get(i).length();
        }
        return sum;
    }
}
