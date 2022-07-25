package Days;

import utility.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {
    ArrayList<String> fileContent = new FileReader("resources/D7/input").fileReaderArrayList();

    public Day7() {
        Map<String, String> nodes = new HashMap<>();
        String checkFor = "a";
        processFileContent(fileContent,null, nodes);
        System.out.println("D7 - The value of " + checkFor + " : " + nodes.get(checkFor));

        String bOutput = nodes.get(checkFor);
        nodes.clear();
        processFileContent(fileContent, bOutput, nodes);
        System.out.println("D7/2 - The value of " + checkFor + " : " + nodes.get(checkFor));

    }

    static boolean isResolved(String val, Map<String, String> nodes) {
        try {
            Integer.parseInt(nodes.get(val));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static boolean resolveDirect(Map<String, String> nodes) {
        boolean isChanged = false;
        ArrayList<String> resolve = new ArrayList<>();
        for (String key : nodes.keySet()) {
            Matcher matcher = Pattern.compile("^(\\d+)$").matcher(nodes.get(key));
            if (matcher.find()) {
                resolve.add(key);
            }
        }
        for (String key : nodes.keySet()) {
            Matcher matcher = Pattern.compile("^(\\D+)$").matcher(nodes.get(key));
            if (matcher.find()) {
                if (resolve.contains(matcher.group(1))) {
                    if (isResolved(matcher.group(1), nodes)) {
                        nodes.replace(key, nodes.get(matcher.group(1)));
                        isChanged = true;
                    }
                }
            }
        }
        return isChanged;
    }

    static boolean replaceResolved(Map<String, String> nodes) {
        boolean isChanged = false;
        for (String key : nodes.keySet()) {
            Matcher matcher = Pattern.compile("NOT\\W(\\D+)").matcher(nodes.get(key));
            if (matcher.find()) {
                if (isResolved(matcher.group(1), nodes)) {
                    nodes.replace(key, "NOT " + nodes.get(matcher.group(1)));
                    isChanged = true;
                }
            }
            matcher = Pattern.compile("(\\d+)\\WAND\\W(\\D+)").matcher(nodes.get(key));
            if (matcher.find()) {
                if (isResolved(matcher.group(2), nodes)) {
                    nodes.replace(key, matcher.group(1) + " AND " + nodes.get(matcher.group(2)));
                    isChanged = true;
                }
            }
            matcher = Pattern.compile("(\\D+)\\WAND\\W(\\d+)").matcher(nodes.get(key));
            if (matcher.find()) {
                if (isResolved(matcher.group(1), nodes)) {
                    nodes.replace(key, nodes.get(matcher.group(1)) + " AND " + matcher.group(2));
                    isChanged = true;
                }
            }
            matcher = Pattern.compile("(\\D+)\\WAND\\W(\\D+)").matcher(nodes.get(key));
            if (matcher.find()) {
                if (isResolved(matcher.group(1), nodes) && isResolved(matcher.group(2), nodes)) {
                    nodes.replace(key, nodes.get(matcher.group(1)) + " AND " + nodes.get(matcher.group(2)));
                    isChanged = true;
                }
            }
            matcher = Pattern.compile("(\\d+)\\WOR\\W(\\D+)").matcher(nodes.get(key));
            if (matcher.find()) {
                if (isResolved(matcher.group(2), nodes)) {
                    nodes.replace(key, matcher.group(1) + " OR " + nodes.get(matcher.group(2)));
                    isChanged = true;
                }
            }
            matcher = Pattern.compile("(\\D+)\\WOR\\W(\\d+)").matcher(nodes.get(key));
            if (matcher.find()) {
                if (isResolved(matcher.group(1), nodes)) {
                    nodes.replace(key, nodes.get(matcher.group(1)) + " OR " + matcher.group(2));
                    isChanged = true;
                }
            }
            matcher = Pattern.compile("(\\D+)\\WOR\\W(\\D+)").matcher(nodes.get(key));
            if (matcher.find()) {
                if (isResolved(matcher.group(1), nodes) && isResolved(matcher.group(2), nodes)) {
                    nodes.replace(key, nodes.get(matcher.group(1)) + " OR " + nodes.get(matcher.group(2)));
                    isChanged = true;
                }
            }
            matcher = Pattern.compile("(\\D+)\\WLSHIFT\\W(\\d+)").matcher(nodes.get(key));
            if (matcher.find()) {
                if (isResolved(matcher.group(1), nodes)) {
                    nodes.replace(key, nodes.get(matcher.group(1)) + " LSHIFT " + matcher.group(2));
                    isChanged = true;
                }
            }
            matcher = Pattern.compile("(\\D+)\\WRSHIFT\\W(\\d+)").matcher(nodes.get(key));
            if (matcher.find()) {
                if (isResolved(matcher.group(1), nodes)) {
                    nodes.replace(key, nodes.get(matcher.group(1)) + " RSHIFT " + matcher.group(2));
                    isChanged = true;
                }
            }
        }
        return isChanged;
    }

    static boolean resolveOpr(Map<String, String> nodes) {
        boolean isChanged = false;
        for (String key : nodes.keySet()) {
            Matcher matcher = Pattern.compile("NOT (\\d+)").matcher(nodes.get(key));
            if (matcher.find()) {
                nodes.replace(key, notOp(Integer.parseInt(matcher.group(1))));
                isChanged = true;
            }
            matcher = Pattern.compile("(\\d+) AND (\\d+)").matcher(nodes.get(key));
            if (matcher.find()) {
                nodes.replace(key, andOp(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))));
                isChanged = true;
            }
            matcher = Pattern.compile("(\\d+) OR (\\d+)").matcher(nodes.get(key));
            if (matcher.find()) {
                nodes.replace(key, orOp(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))));
                isChanged = true;
            }
            matcher = Pattern.compile("(\\d+) LSHIFT (\\d+)").matcher(nodes.get(key));
            if (matcher.find()) {
                nodes.replace(key, lsOp(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))));
                isChanged = true;
            }
            matcher = Pattern.compile("(\\d+) RSHIFT (\\d+)").matcher(nodes.get(key));
            if (matcher.find()) {

                nodes.replace(key, rsOp(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))));
                isChanged = true;
            }
        }
        return isChanged;
    }

    static String rsOp(int value1, int value2) {
        StringBuilder x = new StringBuilder(to16Bin(String.valueOf(value1)));
        for (int i = 0; i < value2; i++) {
            x.insert(0, "0");
            x.deleteCharAt(x.length() - 1);
        }
        return String.valueOf(Integer.parseInt(x.toString(), 2));
    }

    static String lsOp(int value1, int value2) {
        StringBuilder x = new StringBuilder(to16Bin(String.valueOf(value1)));
        for (int i = 0; i < value2; i++) {
            x.append("0");
            x.delete(0, 1);
        }
        return String.valueOf(Integer.parseInt(x.toString(), 2));
    }

    static String orOp(int value1, int value2) {
        StringBuilder x = new StringBuilder(to16Bin(String.valueOf(value1)));
        StringBuilder y = new StringBuilder(to16Bin(String.valueOf(value2)));
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) == '1' || y.charAt(i) == '1') {
                result.append('1');
            } else {
                result.append('0');
            }
        }
        return String.valueOf(Integer.parseInt(result.toString(), 2));
    }

    static String andOp(int value1, int value2) {
        StringBuilder x = new StringBuilder(to16Bin(String.valueOf(value1)));
        StringBuilder y = new StringBuilder(to16Bin(String.valueOf(value2)));
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) == '1' && y.charAt(i) == '1') {
                result.append('1');
            } else {
                result.append('0');
            }
        }
        return String.valueOf(Integer.parseInt(result.toString(), 2));
    }

    static String notOp(int value) {
        StringBuilder x = new StringBuilder(to16Bin(String.valueOf(value)));
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) == '1') {
                x.replace(i, i + 1, "0");

            } else {
                x.replace(i, i + 1, "1");
            }
        }
        return String.valueOf(Integer.parseInt(x.toString(), 2));
    }

    static String to16Bin(String value) {
        StringBuilder x = new StringBuilder(Integer.toBinaryString(Integer.parseInt(value)));
        while (x.length() < 16) {
            x.insert(0, "0");
        }
        return x.toString();
    }

    static void processFileContent(ArrayList<String> fileContent, String bOutput, Map<String, String> nodes) {
        for (String line : fileContent) {
            Matcher matcher = Pattern.compile("(.+) -> (\\w+)").matcher(line);
            if (matcher.find()) {
                if (matcher.group(2).equals("b") && bOutput != null) {
                    nodes.put(matcher.group(2), bOutput);
                } else {
                    nodes.put(matcher.group(2), matcher.group(1));
                }
            }
        }
        while (resolveDirect(nodes) || replaceResolved(nodes) || resolveOpr(nodes)) {
        }
    }
}
