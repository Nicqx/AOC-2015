package Days;

import utility.FileReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day25 {
    String text = new FileReader("resources/D25/input").fileReaderString();
    long start = 20151125;

    public Day25() {
        int[] ints = processFileContent(text);

        System.out.println("D25 - the code in x:" + ints[0] + ", and y:" + ints[1] + ", val: " + calculateCode(start, ints[0], ints[1]));
    }

    static long calculateCode(long start, int reqXCoord, int reqYCoord) {
        int x = 0;
        int y;
        boolean notFound = true;
        int counter = 2;
        do {
            x++;
            y = x;
            for (int i = 1; i <= x; i++) {
                for (int j = y; j >= 1; j--) {
                    if (i + j == counter) {
                        if (i == reqXCoord && j == reqYCoord) {
                            notFound = false;
                            break;
                        }
                        start = genNext(start);
                    }
                }
                if (!notFound) {
                    break;
                }
            }
            counter++;
        } while (notFound);
        return start;
    }

    static int[] processFileContent(String text) {
        int reqXCoord = 0, reqYCoord = 0;
        Matcher matcher = Pattern.compile("To continue, please consult the code grid in the manual\\.  Enter the code at row (\\d+), column (\\d+)\\.").matcher(text);
        if (matcher.find()) {
            reqXCoord = Integer.parseInt(matcher.group(2));
            reqYCoord = Integer.parseInt(matcher.group(1));
        }
        return new int[]{reqXCoord, reqYCoord};
    }

    static long genNext(long val) {
        val *= 252533;
        return val % 33554393;
    }
}
