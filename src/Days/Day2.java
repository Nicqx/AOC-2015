package Days;

import utility.FileReader;

import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Day2 {
    ArrayList<String> fileContent = new FileReader("resources/D2/input").fileReaderArrayList();
    private final ArrayList<Box> boxes = new ArrayList<>();


    public Day2() {
        processFileContent();
        System.out.println("D2 - The elves need to order this amount: " + processWrap());
        System.out.println("D2/2 - The elves used for the ribbon this length: " + processRibbon());
    }

    private void processFileContent() {
        for (String line : fileContent) {
            boxes.add(new Box.BoxBuilder().width(Integer.parseInt(line.split("x")[0])).height(Integer.parseInt(line.split("x")[1])).length(Integer.parseInt(line.split("x")[2])).build());
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
        private final int width;
        private final int height;
        private final int length;

        public int getRibbonNeed() {
            return ((width + height + length - max(width, max(height, length))) * 2 + (width * height * length));
        }

        public int getWrapNeed() {
            int sideAB = width * height;
            int sideBC = height * length;
            int sideAC = width * length;
            return (2 * sideAB) + (2 * sideAC) + (2 * sideBC) + min(sideAB, min(sideAC, sideBC));
        }

        private Box(BoxBuilder builder) {
            this.width = builder.width;
            this.height = builder.height;
            this.length = builder.length;
        }

        public static class BoxBuilder {
            private int width;
            private int height;
            private int length;

            public BoxBuilder() {
            }

            public BoxBuilder width(int width) {
                this.width = width;

                return this;
            }

            public BoxBuilder height(int height) {
                this.height = height;

                return this;
            }

            public BoxBuilder length(int length) {
                this.length = length;

                return this;
            }

            public Box build() {
                return new Box(this);
            }
        }
    }
}
