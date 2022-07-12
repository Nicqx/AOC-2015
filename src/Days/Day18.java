package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day18 {
    boolean[][] grid = new boolean[100][100];
    final int ROUNDS = 100;

    public Day18() {
        fileReader("resources/D18/input");
        process(false);
        System.out.println("D18 - the number of lights are on after the " + ROUNDS + " rounds: " + countLightsOn());

        fileReader("resources/D18/input");
        process(true);
        System.out.println("D18/2 - the number of lights are on after the " + ROUNDS + " rounds with corner locks: " + countLightsOn());
    }

    private void fileReader(String res) {
        try (Scanner scanner = new Scanner(new File(res))) {
            int i = 0;
            while (scanner.hasNext()) {
                int j = 0;
                String row = scanner.nextLine();
                for (int c = 0; c < row.length(); c++) {
                    grid[i][j] = row.charAt(c) == '#';
                    j++;
                }
                i++;
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    private void turnTheCorners() {
        grid[0][0] = true;
        grid[0][99] = true;
        grid[99][0] = true;
        grid[99][99] = true;
    }

    private void process(boolean lock) {
        if (lock) {
            turnTheCorners();
        }
        for (int r = 0; r < ROUNDS; r++) {
            ArrayList<int[]> shouldBeChange = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    if (grid[i][j]) {
                        if (countNeighboursOn(i, j) != 2 && countNeighboursOn(i, j) != 3) {
                            shouldBeChange.add(new int[]{i, j});
                        }
                    } else {
                        if (countNeighboursOn(i, j) == 3) {
                            shouldBeChange.add(new int[]{i, j});
                        }
                    }
                }
            }
            for (int[] ints : shouldBeChange) {
                if (lock) {
                    if (!skipCorners(ints)) {
                        changeLight(ints[0], ints[1]);
                    }
                } else {
                    changeLight(ints[0], ints[1]);
                }
            }
        }
    }

    private boolean skipCorners(int[] ints) {
        return (ints[0] == 0 && ints[1] == 0) || (ints[0] == 0 && ints[1] == 99) || (ints[0] == 99 && ints[1] == 0) || (ints[0] == 99 && ints[1] == 99);
    }

    private int countLightsOn() {
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

    private int countNeighboursOn(int i, int j) {
        int result = 0;
        if (checkUpper(i, j)) {
            result++;
        }
        if (checkLower(i, j)) {
            result++;
        }
        if (checkRight(i, j)) {
            result++;
        }
        if (checkLeft(i, j)) {
            result++;
        }
        if (checkUpperLeft(i, j)) {
            result++;
        }
        if (checkUpperRight(i, j)) {
            result++;
        }
        if (checkLowerLeft(i, j)) {
            result++;
        }
        if (checkLowerRight(i, j)) {
            result++;
        }
        return result;
    }

    private boolean checkUpper(int i, int j) {
        if (i > 0) {
            return grid[i - 1][j];
        } else {
            return false;
        }
    }

    private boolean checkLower(int i, int j) {
        if (i < 99) {
            return grid[i + 1][j];
        } else {
            return false;
        }
    }

    private boolean checkLeft(int i, int j) {
        if (j > 0) {
            return grid[i][j - 1];
        } else {
            return false;
        }
    }

    private boolean checkRight(int i, int j) {
        if (j < 99) {
            return grid[i][j + 1];
        } else {
            return false;
        }
    }

    private boolean checkUpperLeft(int i, int j) {
        if (i > 0 && j > 0) {
            return grid[i - 1][j - 1];
        } else {
            return false;
        }
    }

    private boolean checkUpperRight(int i, int j) {
        if (i > 0 && j < 99) {
            return grid[i - 1][j + 1];
        } else {
            return false;
        }
    }

    private boolean checkLowerLeft(int i, int j) {
        if (i < 99 && j > 0) {
            return grid[i + 1][j - 1];
        } else {
            return false;
        }
    }

    private boolean checkLowerRight(int i, int j) {
        if (i < 99 && j < 99) {
            return grid[i + 1][j + 1];
        } else {
            return false;
        }
    }

    private void changeLight(int i, int j) {
        grid[i][j] = !grid[i][j];
    }


}
