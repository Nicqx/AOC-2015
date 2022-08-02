package Days;

import utility.FileReader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day19 {
    ArrayList<String> fileContent = new FileReader("resources/D19/input").fileReaderArrayList();

    final int REPEATS = 2;  // this could be either increase or decrease to get the correct answer... 207

    public Day19() {
        ArrayList<String[]> replacements = processFileContent(fileContent);
        String initial = getInitial(fileContent);
        System.out.println("D19 - The unique molecules count is: " + doTheReplacements(initial, replacements).size());

        System.out.println("D19/2 - The steps is needed to create the molecule is: " + doReverseReplacements(initial, REPEATS, replacements));
    }

    static String getInitial(ArrayList<String> fileContent) {
        String initial ="";
        for (String line : fileContent) {
            if (line.equals("")) {
                continue;
            }
            Matcher matcher = Pattern.compile("(\\w+) => (\\w+)").matcher(line);
            if (!matcher.find()) {
                initial = line;
            }
        }
        return initial;
    }

    static ArrayList<String[]> processFileContent(ArrayList<String> fileContent) {
        ArrayList<String[]> replacements = new ArrayList<>();
        for (String line : fileContent) {
            if (line.equals("")) {
                continue;
            }
            Matcher matcher = Pattern.compile("(\\w+) => (\\w+)").matcher(line);
            if (matcher.find()) {
                replacements.add(new String[]{matcher.group(1), matcher.group(2)});
            }
        }
        return replacements;
    }

    static Set<String> doTheReplacements(String initial, ArrayList<String[]> replacements) {
        Set<String> newUniqueMolecules = new HashSet<>();
        for (String[] replacement : replacements) {
            int lastIndex = 0;
            int count = countOccurrences(replacement[0], lastIndex, initial);
            newUniqueMolecules.addAll(replaceAllOccurrences(count, initial, replacement[0], replacement[1]));
        }
        return newUniqueMolecules;
    }

    static int doReverseReplacements(String initial, int repeats, ArrayList<String[]> replacements) {
        String storeOldInitial = initial;
        int possibleMinimalValue = Integer.MAX_VALUE;
        for (int i = 0; i < repeats; i++) {
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


    static int countOccurrences(String subString, int lastIndex, String fullString) {
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

    private static Set<String> replaceAllOccurrences(int count, String fullString, String subString, String replacement) {
        Set<String> newUniqueMolecules = new HashSet<>();
        for (int i = 1; i <= count; i++) {
            String replacedText = replaceOccurrence(fullString, subString, replacement, i);
            newUniqueMolecules.add(replacedText);

        }
        return newUniqueMolecules;
    }

    private static String replaceOccurrence(String text, String replaceFrom, String replaceTo, int occurrenceIndex) {
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
