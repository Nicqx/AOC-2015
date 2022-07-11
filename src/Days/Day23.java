package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day23 {
    int a = 0;
    int b = 0;
    int nextCoord = 0;
    ArrayList<String> register = new ArrayList<>();

    public Day23() {

        try (Scanner scanner = new Scanner(new File("resources/D23/input"))) {
            while (scanner.hasNext()) {
                register.add(scanner.nextLine());

            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        boolean result;
        do {
            result = process(nextCoord);
        } while (result);
        System.out.println("D23 - the value in register b: " + b);
        a=1;
        b=0;
        nextCoord = 0;
        do {
            result = process(nextCoord);
        } while (result);
        System.out.println("D23/2 - the value in register b: " + b);
    }

    private boolean process(int coord) {
        if (register.size() < coord + 1 || coord < 0) {
            return false;
        }
        if (register.get(coord).equals("inc a")) {
            a++;
            nextCoord = coord + 1;
            return true;
        }
        if (register.get(coord).equals("inc b")) {
            b++;
            nextCoord = coord + 1;
            return true;
        }
        if (register.get(coord).equals("hlf a")) {
            a /= 2;
            nextCoord = coord + 1;
            return true;
        }
        if (register.get(coord).equals("tpl a")) {
            a *= 3;
            nextCoord++;
            return true;
        }
        if (register.get(coord).startsWith("jmp")) {
            String num = register.get(coord).split(" ")[1];
            if (num.startsWith("+")) {
                nextCoord += Integer.parseInt(num.split("\\+")[1]);
                return true;
            } else {
                nextCoord -= Integer.parseInt(num.split("-")[1]);
                return true;
            }
        }
        Matcher matcher = Pattern.compile("jie a, \\+(\\d+)").matcher(register.get(coord));
        if (matcher.find()) {
            if (a % 2 == 0) {
                nextCoord += Integer.parseInt(matcher.group(1));
                return true;
            } else {
                nextCoord++;
                return true;
            }
        }
        matcher = Pattern.compile("jio a, \\+(\\d+)").matcher(register.get(coord));
        if (matcher.find()) {
            if (a == 1) {
                nextCoord += Integer.parseInt(matcher.group(1));
                return true;
            } else {
                nextCoord++;
                return true;
            }
        }
        return true;
    }
}
