package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day12 {
    public Day12() {
        int sum = 0;
        try (Scanner scanner = new Scanner(new File("resources/D12/input"))) {
            while (scanner.hasNext()) {
                sum = sumNum(scanner.nextLine());
            }
            System.out.println("D12 - the sum of all numbers: " + sum);

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        try (Scanner scanner = new Scanner(new File("resources/D12/input"))) {
            while (scanner.hasNext()) {
                sum = sumNum(omitRed(scanner.nextLine()));
            }
            System.out.println("D12/2 - the sum of all numbers without red: " + sum);

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    private String omitRed(String text) {
        Stack<String> stack = new Stack<>();
        StringBuilder newString = new StringBuilder(text);
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '{') {
                stack.push(i + "," + false);
            }
            if (text.charAt(i) == ':'
                    && text.charAt(i + 1) == '\"'
                    && text.charAt(i + 2) == 'r'
                    && text.charAt(i + 3) == 'e'
                    && text.charAt(i + 4) == 'd'
                    && text.charAt(i + 5) == '\"') {
                String tmp = stack.pop();
                stack.push(tmp.split(",")[0] + "," + true);

            }
            if (text.charAt(i) == '}') {
                if (stack.peek().split(",")[1].equals("false")) {
                    stack.pop();
                } else {
                    for (int j = Integer.parseInt(stack.pop().split(",")[0]); j <= i; j++) {
                        newString.setCharAt(j, '0');
                    }
                }
            }
        }
        return newString.toString();
    }

    private int sumNum(String text) {
        int sum = 0;
        Matcher matcher = Pattern.compile("(-?\\d+)").matcher(text);
        while (matcher.find()) {
            sum += Integer.parseInt(matcher.group());
        }
        return sum;
    }
}
