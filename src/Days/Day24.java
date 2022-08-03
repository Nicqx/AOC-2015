package Days;

import utility.FileReader;

import java.util.ArrayList;

public class Day24 {
    ArrayList<String> fileContent = new FileReader("resources/D24/input").fileReaderArrayList();

    public Day24() {
        ArrayList<Integer> numbers = processFileContent(fileContent);
        ArrayList<ArrayList<Integer>> variations = new ArrayList<>();

        generateVariations(3, variations, numbers);
        System.out.println("D24 - the QE for the first group: " + minimalQEinMinimalPiece(variations));
        generateVariations(4, variations, numbers);
        System.out.println("D24/2 - the QE for the first group with 4 place: " + minimalQEinMinimalPiece(variations));
    }

    static ArrayList<Integer> processFileContent(ArrayList<String> fileContent) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (String line : fileContent) {
            numbers.add(Integer.parseInt(line));
        }
        return numbers;
    }

    static long minimalQEinMinimalPiece(ArrayList<ArrayList<Integer>> variations) {
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

    static void generateVariations(int div, ArrayList<ArrayList<Integer>> variations, ArrayList<Integer> numbers) {
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
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
            for (int y = 0; y < Length; y++) {
                localList.add(Empty_Array[y]);
            }

            if (localList.stream().mapToInt(Integer::intValue).sum() == sum / div) {
                variations.add(localList);
            }
            return;
        }

        if (x >= n) {
            return;
        }

        Empty_Array[Array_Index] = Input_Array[x];
        PossibleCombinations(Input_Array, n, Length, Array_Index + 1, Empty_Array, x + 1, variations, sum, div);

        PossibleCombinations(Input_Array, n, Length, Array_Index, Empty_Array, x + 1, variations, sum, div);
    }

    private static void Print_Combination(int[] Input_Array, int n, int Length, ArrayList<ArrayList<Integer>> variations, int sum, int div) {
        int[] Empty_Array = new int[Length];

        PossibleCombinations(Input_Array, n, Length, 0, Empty_Array, 0, variations, sum, div);
    }
}