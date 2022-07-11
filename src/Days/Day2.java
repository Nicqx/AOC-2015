package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Day2 {
    private static int ribbon = 0;

    public Day2() {
        System.out.println("D2 - The elves need to order this amount: " + fileReader("resources/D2/input"));
        System.out.println("D2/2 - The elves used for the ribbon this length: " + ribbon);
    }

    private int fileReader(String res) {
        int surface = 0;
        try (Scanner scanner = new Scanner(new File(res))) {
            while (scanner.hasNext()) {
                surface += process(scanner.nextLine());
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        return surface;
    }

    private static int process(String text) {
        int a = Integer.parseInt(text.split("x")[0]);
        int b = Integer.parseInt(text.split("x")[1]);
        int c = Integer.parseInt(text.split("x")[2]);
        int sideAB = a * b;
        int sideBC = b * c;
        int sideAC = a * c;
        ribbon += ((a + b + c - max(a, max(b, c))) * 2 + (a * b * c));
        return (2 * sideAB) + (2 * sideAC) + (2 * sideBC) + min(sideAB, min(sideAC, sideBC));
    }
}
