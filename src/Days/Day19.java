package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day19 {
    ArrayList<String[]> replacements = new ArrayList<>();
    ArrayList<String[]> initials = new ArrayList<>();
    Set<String> newUniqueMolecules = new HashSet<>();
    String initial;

    public Day19() {
        try (Scanner scanner = new Scanner(new File("resources/D19/input"))) {
            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                if (input.equals("")) {
                    continue;
                }
                Matcher matcher = Pattern.compile("(\\w+) => (\\w+)").matcher(input);
                if (matcher.find()) {
                    if (matcher.group(1).equals("e")) {
                        initials.add(new String[]{matcher.group(1), matcher.group(2)});
                    }
                    replacements.add(new String[]{matcher.group(1), matcher.group(2)});
                } else {
                    initial = input;
                }
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        doTheReplacements(initial, false);
        System.out.println("D19 - The unique molecules count is: " + newUniqueMolecules.size());
        newUniqueMolecules.clear();
        addInitials();
        int steps = 1;
        while (!testNewMolecules()) {
            steps++;
            System.out.println(steps);
            Set<String> tempSet = Set.copyOf(newUniqueMolecules);
            for (String molecules : tempSet) {
                if (testNewMolecules()) {
                    break;
                }
                doTheReplacements(molecules, true);
            }
        }

        System.out.println("D19/2 - The medicine created at minimal steps: " + steps);
    }

    private void addInitials() {
        for (String[] strings : initials) {
            newUniqueMolecules.add(strings[1]);
        }
    }

    private void doTheReplacements(String initial, boolean test) {
        for (String[] replacement : replacements) {
            int lastIndex = 0;
            int count = countOccurrences(replacement[0], lastIndex, initial);
            replaceAllOccurrences(count, initial, replacement[0], replacement[1], test);
        }
    }

    private boolean testNewMolecules() {
        return newUniqueMolecules.contains(initial);
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

    private void replaceAllOccurrences(int count, String fullString, String subString, String replacement, boolean test) {
        for (int i = 1; i <= count; i++) {
            String replacedText = replaceOccurrence(fullString, subString, replacement, i);
            if (!test) {
                newUniqueMolecules.add(replacedText);
            } else {
                if (replacedText.length() <= initial.length()) {
                    newUniqueMolecules.add(replacedText);
                    newUniqueMolecules.remove(fullString);
                }
            }
        }
    }

    private String replaceOccurrence(String text, String replaceFrom, String replaceTo, int occuranceIndex) {
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(replaceFrom);
        Matcher m = p.matcher(text);
        int count = 0;
        while (m.find()) {
            if (count++ == occuranceIndex - 1) {
                m.appendReplacement(sb, replaceTo);
            }
        }
        m.appendTail(sb);
        return sb.toString();
    }

}
