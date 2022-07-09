package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day24 {
    ArrayList<Integer> numbers = new ArrayList<>();
    ArrayList<ArrayList<Integer>> variations = new ArrayList<>();
    int sum;

    public Day24() {
        try (Scanner scanner = new Scanner(new File("resources/D24/input"))) {
            while (scanner.hasNext()) {
                numbers.add(Integer.parseInt(scanner.nextLine()));
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        sum = numbers.stream().mapToInt(Integer::intValue).sum();
        generateVariations(3);
        System.out.println("D24 - the QE for the first group: " + minimalQEinMinimalPiece());
        generateVariations(4);
        System.out.println("D24/2 - the QE for the first group with 4 place: " + minimalQEinMinimalPiece());
    }

    private long minimalQEinMinimalPiece() {
        long minQE = Long.MAX_VALUE;
        int minPiece = Integer.MAX_VALUE;
        for (ArrayList<Integer> element : variations) {
            if (element.size() < minPiece) {
                minPiece = element.size();
            }
        }
        int finalMinPiece = minPiece;
        variations.removeAll(variations.stream().filter(a -> a.size() > finalMinPiece).toList());
        for (ArrayList<Integer> element : variations) {
            if (element.size() == minPiece) {
                long subSum = 1;
                for (int elem : element) {
                    subSum *= elem;
                    if (subSum > minQE) {
                        break;
                    }
                }
                if (subSum < minQE) {
                    minQE = subSum;
                }
            }
        }


        return minQE;
    }

    private void generateVariations(int div) {
        variations.clear();
        int[] Input_Array = numbers.stream().mapToInt(i -> i).toArray();
        int n = Input_Array.length;
        for (int i = 1; i <= numbers.size(); i++) {
            Print_Combination(Input_Array, n, i, variations, sum, div);
        }
    }

    private static void PossibleCombinations(int[] Input_Array, int n, int Length, int Array_Index,
                                             int[] Empty_Array, int x, ArrayList<ArrayList<Integer>> variations, int sum, int div) {
        ArrayList<Integer> localList = new ArrayList<>();
        if (Array_Index == Length) {
            for (int y = 0; y < Length; y++)
                localList.add(Empty_Array[y]);

            if (localList.stream().mapToInt(Integer::intValue).sum() == sum / div) {
                variations.add(localList);
            }

            return;
        }

        if (x >= n)
            return;

        Empty_Array[Array_Index] = Input_Array[x];
        PossibleCombinations(Input_Array, n, Length, Array_Index + 1, Empty_Array, x + 1, variations, sum, div);

        PossibleCombinations(Input_Array, n, Length, Array_Index, Empty_Array, x + 1, variations, sum, div);
    }

    private static void Print_Combination(int[] Input_Array, int n, int Length, ArrayList<ArrayList<Integer>> variations, int sum, int div) {
        int[] Empty_Array = new int[Length];

        PossibleCombinations(Input_Array, n, Length, 0, Empty_Array, 0, variations, sum, div);
    }
}