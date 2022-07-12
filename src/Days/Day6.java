package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6 {
    static private final int[][] matrix_old = new int[1000][1000];
    static private final int[][] matrix_new = new int[1000][1000];

    public Day6() {
        init();
        init_new();
        fileReader("resources/D6/input");
        System.out.println("D6 - These lights are on: " + readOut());
        System.out.println("D6/2 - These lights are on ba the new code: " + readOut_new());

    }

    private void fileReader(String res) {
        try (Scanner scanner = new Scanner(new File(res))) {
            while (scanner.hasNext()) {
                String text = scanner.nextLine();
                process(text);
                process2(text);
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    private static void process(String text) {
        Matcher onMatcher = Pattern.compile("turn on (\\d+),(\\d+) through (\\d+),(\\d+)").matcher(text);
        Matcher offMatcher = Pattern.compile("turn off (\\d+),(\\d+) through (\\d+),(\\d+)").matcher(text);
        Matcher toggleMatcher = Pattern.compile("toggle (\\d+),(\\d+) through (\\d+),(\\d+)").matcher(text);

        if (onMatcher.find()) {
            turnOn(Integer.parseInt(onMatcher.group(1)),
                    Integer.parseInt(onMatcher.group(2)),
                    Integer.parseInt(onMatcher.group(3)),
                    Integer.parseInt(onMatcher.group(4)));
        }
        if (offMatcher.find()) {
            turnOut(Integer.parseInt(offMatcher.group(1)),
                    Integer.parseInt(offMatcher.group(2)),
                    Integer.parseInt(offMatcher.group(3)),
                    Integer.parseInt(offMatcher.group(4)));
        }
        if (toggleMatcher.find()) {
            toggle(Integer.parseInt(toggleMatcher.group(1)),
                    Integer.parseInt(toggleMatcher.group(2)),
                    Integer.parseInt(toggleMatcher.group(3)),
                    Integer.parseInt(toggleMatcher.group(4)));
        }
    }

    private static void process2(String text) {
        Matcher onMatcher = Pattern.compile("turn on (\\d+),(\\d+) through (\\d+),(\\d+)").matcher(text);
        Matcher offMatcher = Pattern.compile("turn off (\\d+),(\\d+) through (\\d+),(\\d+)").matcher(text);
        Matcher toggleMatcher = Pattern.compile("toggle (\\d+),(\\d+) through (\\d+),(\\d+)").matcher(text);

        if (onMatcher.find()) {
            turnOn_new(Integer.parseInt(onMatcher.group(1)),
                    Integer.parseInt(onMatcher.group(2)),
                    Integer.parseInt(onMatcher.group(3)),
                    Integer.parseInt(onMatcher.group(4)));
        }
        if (offMatcher.find()) {
            turnOut_new(Integer.parseInt(offMatcher.group(1)),
                    Integer.parseInt(offMatcher.group(2)),
                    Integer.parseInt(offMatcher.group(3)),
                    Integer.parseInt(offMatcher.group(4)));
        }
        if (toggleMatcher.find()) {
            toggle_new(Integer.parseInt(toggleMatcher.group(1)),
                    Integer.parseInt(toggleMatcher.group(2)),
                    Integer.parseInt(toggleMatcher.group(3)),
                    Integer.parseInt(toggleMatcher.group(4)));
        }
    }

    private static void turnOut(int a, int b, int c, int d) {
        for (int x = a; x <= c; x++) {
            for (int y = b; y <= d; y++) {
                matrix_old[x][y] = -1;
            }
        }
    }

    private static void turnOut_new(int a, int b, int c, int d) {
        for (int x = a; x <= c; x++) {
            for (int y = b; y <= d; y++) {
                if (matrix_new[x][y] > 0) {
                    matrix_new[x][y] -= 1;
                }
            }
        }
    }

    private static void turnOn(int a, int b, int c, int d) {
        for (int x = a; x <= c; x++) {
            for (int y = b; y <= d; y++) {
                matrix_old[x][y] = 1;
            }
        }
    }

    private static void turnOn_new(int a, int b, int c, int d) {
        for (int x = a; x <= c; x++) {
            for (int y = b; y <= d; y++) {
                matrix_new[x][y] += 1;
            }
        }
    }

    private static void toggle(int a, int b, int c, int d) {
        for (int x = a; x <= c; x++) {
            for (int y = b; y <= d; y++) {
                matrix_old[x][y] *= -1;
            }
        }
    }

    private static void toggle_new(int a, int b, int c, int d) {
        for (int x = a; x <= c; x++) {
            for (int y = b; y <= d; y++) {
                matrix_new[x][y] += 2;
            }
        }
    }

    private static void init() {
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                matrix_old[i][j] = -1;
            }
        }
    }

    private static void init_new() {
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                matrix_new[i][j] = 0;
            }
        }
    }

    private static int readOut() {
        int counter = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (matrix_old[i][j] == 1) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static int readOut_new() {
        int counter = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                counter += matrix_new[i][j];

            }
        }
        return counter;
    }
}
