package Days;

import utility.FileReader;

import java.util.ArrayList;

public class Day18 {
    ArrayList<String> fileContent = new FileReader("resources/D18/input").fileReaderArrayList();
    boolean[][] grid = new boolean[100][100];
    final int ROUNDS = 100;

    public Day18() {
        processFileContent(fileContent, grid);
        process(false, grid, ROUNDS);
        System.out.println("D18 - the number of lights are on after the " + ROUNDS + " rounds: " + countLightsOn(grid));

        processFileContent(fileContent, grid);
        process(true, grid, ROUNDS);
        System.out.println("D18/2 - the number of lights are on after the " + ROUNDS + " rounds with corner locks: " + countLightsOn(grid));
    }

    static void processFileContent(ArrayList<String> fileContent, boolean[][] grid) {
        int i = 0;
        for (String line : fileContent) {
            int j = 0;
            for (int c = 0; c < line.length(); c++) {
                grid[i][j] = line.charAt(c) == '#';
                j++;
            }
            i++;
        }
    }

    static void turnTheCorners(boolean[][] grid) {
        grid[0][0] = true;
        grid[0][99] = true;
        grid[99][0] = true;
        grid[99][99] = true;
    }

    static void process(boolean lock, boolean[][] grid, int rounds) {
        if (lock) {
            turnTheCorners(grid);
        }
        for (int r = 0; r < rounds; r++) {
            ArrayList<int[]> shouldBeChange = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    if (grid[i][j]) {
                        if (countNeighboursOn(i, j, grid) != 2 && countNeighboursOn(i, j, grid) != 3) {
                            shouldBeChange.add(new int[]{i, j});
                        }
                    } else {
                        if (countNeighboursOn(i, j, grid) == 3) {
                            shouldBeChange.add(new int[]{i, j});
                        }
                    }
                }
            }
            for (int[] ints : shouldBeChange) {
                if (lock) {
                    if (!skipCorners(ints)) {
                        changeLight(ints[0], ints[1], grid);
                    }
                } else {
                    changeLight(ints[0], ints[1], grid);
                }
            }
        }
    }

    static boolean skipCorners(int[] ints) {
        return (ints[0] == 0 && ints[1] == 0) || (ints[0] == 0 && ints[1] == 99) || (ints[0] == 99 && ints[1] == 0) || (ints[0] == 99 && ints[1] == 99);
    }

    static int countLightsOn(boolean[][] grid) {
        int result = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (grid[i][j]) {
                    result++;
                }
            }
        }
        return result;
    }

    static int countNeighboursOn(int i, int j, boolean[][] grid) {
        int result = 0;
        if (checkUpper(i, j,grid)) {
            result++;
        }
        if (checkLower(i, j, grid)) {
            result++;
        }
        if (checkRight(i, j, grid)) {
            result++;
        }
        if (checkLeft(i, j, grid)) {
            result++;
        }
        if (checkUpperLeft(i, j, grid)) {
            result++;
        }
        if (checkUpperRight(i, j, grid)) {
            result++;
        }
        if (checkLowerLeft(i, j, grid)) {
            result++;
        }
        if (checkLowerRight(i, j, grid)) {
            result++;
        }
        return result;
    }

    static boolean checkUpper(int i, int j, boolean[][] grid) {
        if (i > 0) {
            return grid[i - 1][j];
        } else {
            return false;
        }
    }

    static boolean checkLower(int i, int j, boolean[][] grid) {
        if (i < 99) {
            return grid[i + 1][j];
        } else {
            return false;
        }
    }

    static boolean checkLeft(int i, int j, boolean[][] grid) {
        if (j > 0) {
            return grid[i][j - 1];
        } else {
            return false;
        }
    }

    static boolean checkRight(int i, int j, boolean[][] grid) {
        if (j < 99) {
            return grid[i][j + 1];
        } else {
            return false;
        }
    }

    static boolean checkUpperLeft(int i, int j, boolean[][] grid) {
        if (i > 0 && j > 0) {
            return grid[i - 1][j - 1];
        } else {
            return false;
        }
    }

    static boolean checkUpperRight(int i, int j, boolean[][] grid) {
        if (i > 0 && j < 99) {
            return grid[i - 1][j + 1];
        } else {
            return false;
        }
    }

    static boolean checkLowerLeft(int i, int j, boolean[][] grid) {
        if (i < 99 && j > 0) {
            return grid[i + 1][j - 1];
        } else {
            return false;
        }
    }

    static boolean checkLowerRight(int i, int j, boolean[][] grid) {
        if (i < 99 && j < 99) {
            return grid[i + 1][j + 1];
        } else {
            return false;
        }
    }

    static void changeLight(int i, int j, boolean[][] grid) {
        grid[i][j] = !grid[i][j];
    }


}
