package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day19 {
    ArrayList<String[]> replacements = new ArrayList<>();
    Set<String> newUniqueMolecules = new HashSet<>();
    String initial;

    final int REPEATS = 2;  // this could be either increase or decrease to get the correct answer... 207

    public Day19() {
        fileReader("resources/D19/input");
        doTheReplacements(initial);
        System.out.println("D19 - The unique molecules count is: " + newUniqueMolecules.size());

        System.out.println("D19/2 - The steps is needed to create the molecule is: " + doReverseReplacements(initial));
    }

    private void fileReader(String res) {
        try (Scanner scanner = new Scanner(new File(res))) {
            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                if (input.equals("")) {
                    continue;
                }
                Matcher matcher = Pattern.compile("(\\w+) => (\\w+)").matcher(input);
                if (matcher.find()) {
                    replacements.add(new String[]{matcher.group(1), matcher.group(2)});
                } else {
                    initial = input;
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    private void doTheReplacements(String initial) {
        for (String[] replacement : replacements) {
            int lastIndex = 0;
            int count = countOccurrences(replacement[0], lastIndex, initial);
            replaceAllOccurrences(count, initial, replacement[0], replacement[1]);
        }
    }

    private int doReverseReplacements(String initial) {
        String storeOldInitial = initial;
        int possibleMinimalValue = Integer.MAX_VALUE;
        for (int i = 0; i < REPEATS; i++) {
            int steps = 0;
            boolean invalid = false;
            initial = storeOldInitial;
            while (!Objects.equals(initial, "e")) {
                if (invalid) {
                    Collections.shuffle(replacements);
                    invalid = false;
                    initial = storeOldInitial;
                }
                String tmp = initial;
                for (String[] replacement : replacements) {
                    steps += countOccurrences(replacement[1], 0, initial);
                    initial = initial.replaceAll(replacement[1], replacement[0]);
                }
                if (tmp.equals(initial)) {
                    invalid = true;
                }
            }
            if (steps < possibleMinimalValue) {
                possibleMinimalValue = steps;
            }
        }
        return possibleMinimalValue;
    }


    private int countOccurrences(String subString, int lastIndex, String fullString) {
        int result = 0;
        while (lastIndex != -1) {

            lastIndex = fullString.indexOf(subString, lastIndex);

            if (lastIndex != -1) {
                result++;
                lastIndex += subString.length();
            }
        }
        return result;
    }

    private void replaceAllOccurrences(int count, String fullString, String subString, String replacement) {
        for (int i = 1; i <= count; i++) {
            String replacedText = replaceOccurrence(fullString, subString, replacement, i);
            newUniqueMolecules.add(replacedText);

        }
    }

    private String replaceOccurrence(String text, String replaceFrom, String replaceTo, int occurrenceIndex) {
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile(replaceFrom);
        Matcher m = p.matcher(text);
        int count = 0;
        while (m.find()) {
            if (count++ == occurrenceIndex - 1) {
                m.appendReplacement(sb, replaceTo);
            }
        }
        m.appendTail(sb);
        return sb.toString();
    }


}
