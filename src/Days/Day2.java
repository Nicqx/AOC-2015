package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Day2 {
    private final ArrayList<Box> boxes = new ArrayList<>();

    public Day2() {
        fileReader("resources/D2/input");
        System.out.println("D2 - The elves need to order this amount: " + processWrap());
        System.out.println("D2/2 - The elves used for the ribbon this length: " + processRibbon());
    }

    private void fileReader(String res) {
        try (Scanner scanner = new Scanner(new File(res))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                boxes.add(new Box.BoxBuilder(Integer.parseInt(line.split("x")[0]), Integer.parseInt(line.split("x")[1]), Integer.parseInt(line.split("x")[2])).build());
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    private int processRibbon() {
        int completeRibbonLength = 0;
        for (Box box : boxes) {
            completeRibbonLength += box.getRibbonNeed();
        }
        return completeRibbonLength;
    }

    private int processWrap() {
        int completeSurface = 0;
        for (Box box : boxes) {
            completeSurface += box.getWrapNeed();
        }
        return completeSurface;
    }

    public static class Box {
        private final int a;
        private final int b;
        private final int c;

        public int getRibbonNeed() {
            return ((a + b + c - max(a, max(b, c))) * 2 + (a * b * c));
        }

        public int getWrapNeed() {
            int sideAB = a * b;
            int sideBC = b * c;
            int sideAC = a * c;
            return (2 * sideAB) + (2 * sideAC) + (2 * sideBC) + min(sideAB, min(sideAC, sideBC));
        }

        private Box(BoxBuilder builder) {
            this.a = builder.a;
            this.b = builder.b;
            this.c = builder.c;
        }

        public static class BoxBuilder {
            private final int a;
            private final int b;
            private final int c;

            public BoxBuilder(int a, int b, int c) {
                this.a = a;
                this.b = b;
                this.c = c;
            }

            public Box build() {
                return new Box(this);
            }
        }
    }
}
