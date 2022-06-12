package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day3 {
    private static final Set<String> coords1 = new HashSet<>();
    private static final Set<String> coords2 = new HashSet<>();

    public Day3() {
        try (Scanner scanner = new Scanner(new File("resources/D3/input"))) {
            while (scanner.hasNext()) {
                String text = scanner.nextLine();
                System.out.println("D3 - These amount of houses was visited by Santa at least once: " + process1(text));
                System.out.println("D3/2 - These amount of houses was visited with Robo-Santa at least once: " + process2(text));
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    private static int process1(String text) {
        int x = 0;
        int y = 0;
        Day3.coords1.add("0;0");
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '^') {
                x++;
                Day3.coords1.add(x + ";" + y);
            } else if (text.charAt(i) == '>') {
                y++;
                Day3.coords1.add(x + ";" + y);
            } else if (text.charAt(i) == '<') {
                y--;
                Day3.coords1.add(x + ";" + y);
            } else if (text.charAt(i) == 'v') {
                x--;
                Day3.coords1.add(x + ";" + y);
            }

        }
        return Day3.coords1.size();

    }

    private static int process2(String text) {
        int Sx = 0;
        int Sy = 0;
        int Rx = 0;
        int Ry = 0;
        Day3.coords2.add("0;0");
        for (int i = 0; i < text.length(); i++) {
            if (i % 2 == 0) {
                if (text.charAt(i) == '^') {
                    Sx++;
                    Day3.coords2.add(Sx + ";" + Sy);
                } else if (text.charAt(i) == '>') {
                    Sy++;
                    Day3.coords2.add(Sx + ";" + Sy);
                } else if (text.charAt(i) == '<') {
                    Sy--;
                    Day3.coords2.add(Sx + ";" + Sy);
                } else if (text.charAt(i) == 'v') {
                    Sx--;
                    Day3.coords2.add(Sx + ";" + Sy);
                }
            } else {
                if (text.charAt(i) == '^') {
                    Rx++;
                    Day3.coords2.add(Rx + ";" + Ry);
                } else if (text.charAt(i) == '>') {
                    Ry++;
                    Day3.coords2.add(Rx + ";" + Ry);
                } else if (text.charAt(i) == '<') {
                    Ry--;
                    Day3.coords2.add(Rx + ";" + Ry);
                } else if (text.charAt(i) == 'v') {
                    Rx--;
                    Day3.coords2.add(Rx + ";" + Ry);
                }
            }
        }
        return Day3.coords2.size();

    }
}
