package Days;

import utility.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day20 {
    String text = new FileReader("resources/D20/input").fileReaderString();
    static Map<Integer, Integer> globalDividers = new HashMap<>();

    public Day20() {
        System.out.println("D20 - The minimal number of house which got the requested amount of presents: " + processFirstPart(Integer.parseInt(text)));
        System.out.println("D20/2 - The minimal number of house which got the requested amount of presents with the new rules: " + processSecondPart(Integer.parseInt(text), globalDividers));
    }

    static int processFirstPart(int requestedPresents) {
        int i = 0;
        int result;
        do {
            i++;
            result = sumDividers(i);
        } while (result < requestedPresents);
        return i;
    }

    static int processSecondPart(int requestedPresents, Map<Integer, Integer> globalDividers) {
        int i = 0;
        int result;
        do {
            i++;
            result = sumDividersSecond(i, globalDividers);
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

    private static int sumDividersSecond(int value, Map<Integer, Integer> globalDividers) {
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
