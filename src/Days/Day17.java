package Days;

import utility.FileReader;

import java.util.ArrayList;

public class Day17 {
    ArrayList<String> fileContent = new FileReader("resources/D17/input").fileReaderArrayList();
    private final int ALLAMOUNT = 150;
    ArrayList<Integer> containers = new ArrayList<>();
    ArrayList<ArrayList<Integer>> variations = new ArrayList<>();

    public Day17() {
        processFileContent();
        generateVariations();
        System.out.println("D17 - The possible variations to store all eggnog: " + getValidVariations());
        System.out.println("D17/2 - The possible different variations to store all eggnog: " + getMinimalValidVariations());

    }

    private void processFileContent() {
        for (String line : fileContent) {
            containers.add(Integer.parseInt(line));
        }
    }

    private int getMinimalValidVariations() {
        int minimalLength = Integer.MAX_VALUE;
        for (ArrayList<Integer> variation : variations) {
            if (variation.size() < minimalLength) {
                minimalLength = variation.size();
            }
        }
        int counter = 0;
        for (ArrayList<Integer> variation : variations) {
            if (variation.size() == minimalLength) {
                counter++;
            }
        }

        return counter;
    }

    private int getValidVariations() {
        ArrayList<ArrayList<Integer>> tempVariations = new ArrayList<>();
        for (ArrayList<Integer> variation : variations) {
            if (variation.stream().mapToDouble(a -> a).sum() == ALLAMOUNT) {
                tempVariations.add(variation);
            }
        }
        variations = tempVariations;
        return variations.size();
    }

    private void generateVariations() {
        variations.clear();
        int[] Input_Array = containers.stream().mapToInt(i -> i).toArray();
        int n = Input_Array.length;
        for (int i = 1; i <= containers.size(); i++) {
            Print_Combination(Input_Array, n, i, variations);
        }
    }

    private static void PossibleCombinations(int[] Input_Array, int n, int Length, int Array_Index,
                                             int[] Empty_Array, int x, ArrayList<ArrayList<Integer>> variations) {
        ArrayList<Integer> localList = new ArrayList<>();
        if (Array_Index == Length) {
            for (int y = 0; y < Length; y++)
                localList.add(Empty_Array[y]);
            variations.add(localList);
            return;
        }

        if (x >= n)
            return;

        Empty_Array[Array_Index] = Input_Array[x];
        PossibleCombinations(Input_Array, n, Length, Array_Index + 1, Empty_Array, x + 1, variations);

        PossibleCombinations(Input_Array, n, Length, Array_Index, Empty_Array, x + 1, variations);
    }

    private static void Print_Combination(int[] Input_Array, int n, int Length, ArrayList<ArrayList<Integer>> variations) {
        int[] Empty_Array = new int[Length];

        PossibleCombinations(Input_Array, n, Length, 0, Empty_Array, 0, variations);
    }
}
