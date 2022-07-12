package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day20 {
    int requestedPresents;
    static Map<Integer, Integer> globalDividers = new HashMap<>();

    public Day20() {
        requestedPresents = fileReader("resources/D20/input");
        System.out.println("D20 - The minimal number of house which got the requested amount of presents: " + processFirstPart());
        System.out.println("D20/2 - The minimal number of house which got the requested amount of presents with the new rules: " + processSecondPart());
    }

    private int fileReader(String res) {
        int result = 0;
        try (Scanner scanner = new Scanner(new File(res))) {
            result = Integer.parseInt(scanner.nextLine());

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        return result;
    }

    private int processFirstPart() {
        int i = 0;
        int result;
        do {
            i++;
            result = sumDividers(i);
        } while (result < requestedPresents);
        return i;
    }

    private int processSecondPart() {
        int i = 0;
        int result;
        do {
            i++;
            result = sumDividersSecond(i);
        } while (result < requestedPresents);
        return i;
    }

    private static int sumDividers(int value) {
        ArrayList<Integer> dividers = new ArrayList<>();
        for (int i = value; i > 0; i--) {
            if (value % i == 0) {
                dividers.add(i);
            }
        }
        return dividers.stream().mapToInt(Integer::intValue).sum() * 10;
    }

    private static int sumDividersSecond(int value) {
        ArrayList<Integer> dividers = new ArrayList<>();
        for (int i = value; i > 0; i--) {
            if (value % i == 0) {
                if (globalDividers.containsKey(i) && globalDividers.get(i) < 50) {
                    globalDividers.put(i, globalDividers.get(i) + 1);
                    dividers.add(i);
                } else if (!globalDividers.containsKey(i)) {
                    globalDividers.put(i, 1);
                    dividers.add(i);

                }
            }
        }
        return dividers.stream().mapToInt(Integer::intValue).sum() * 11;
    }

}
