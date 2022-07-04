package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day20 {
    int requestedPresents;
    static Map<Integer, Integer> globalDividors = new HashMap<>();

    public Day20() {
        try (Scanner scanner = new Scanner(new File("resources/D20/input"))) {
            requestedPresents = Integer.parseInt(scanner.nextLine());

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        System.out.println("D20 - The minimal number of house which got the requested amount of presents: " + processFirstPart());
        System.out.println("D20/2 - The minimal number of house which got the requested amount of presents with the new rules: " + processSecondPart());
    }

    private int processFirstPart() {
        int i = 0;
        int result;
        do {
            i++;
            result = sumDividors(i);
        } while (result < requestedPresents);
        return i;
    }

    private int processSecondPart() {
        int i = 0;
        int result;
        do {
            i++;
            result = sumDividorsSecond(i);
        } while (result < requestedPresents);
        return i;
    }

    private static int sumDividors(int value) {
        ArrayList<Integer> dividors = new ArrayList<>();
        for (int i = value; i > 0; i--) {
            if (value % i == 0) {
                dividors.add(i);
            }
        }
        return dividors.stream().mapToInt(Integer::intValue).sum() * 10;
    }

    private static int sumDividorsSecond(int value) {
        ArrayList<Integer> dividors = new ArrayList<>();
        for (int i = value; i > 0; i--) {
            if (value % i == 0) {
                if (globalDividors.containsKey(i) && globalDividors.get(i) < 50) {
                    globalDividors.put(i, globalDividors.get(i) + 1);
                    dividors.add(i);
                } else if (!globalDividors.containsKey(i)) {
                    globalDividors.put(i, 1);
                    dividors.add(i);

                }
            }
        }
        return dividors.stream().mapToInt(Integer::intValue).sum() * 11;
    }

}
