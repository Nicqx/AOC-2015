package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day25 {
        long start = 20151125;

    public Day25() {
        int x = 0;
        int y;
        int reqXCoord = 0;
        int reqYCoord = 0;
        try (Scanner scanner = new Scanner(new File("resources/D25/input"))) {
            while (scanner.hasNext()) {
                Matcher matcher = Pattern.compile("To continue, please consult the code grid in the manual\\.  Enter the code at row (\\d+), column (\\d+)\\.").matcher(scanner.nextLine());
                if (matcher.find()) {
                    reqXCoord = Integer.parseInt(matcher.group(2));
                    reqYCoord = Integer.parseInt(matcher.group(1));
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
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
        System.out.println("D25 - the code in x:" + reqXCoord + ", and y:" + reqYCoord + ", val: " + start);
    }

    private long genNext(long val) {
        val *= 252533;
        return val % 33554393;
    }
}
