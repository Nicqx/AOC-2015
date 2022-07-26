package Days;

import utility.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9 {
    ArrayList<String> fileContent = new FileReader("resources/D9/input").fileReaderArrayList();
    ArrayList<Distances> distanceMatrix = new ArrayList<>();

    public Day9() {
        processFileContent(fileContent, distanceMatrix);
        System.out.println("D9 - the shortest distance is: " + calculateDistances(distanceMatrix, true));
        System.out.println("D9/2 - the longest distance is: " + calculateDistances(distanceMatrix, false));
    }

    static void processFileContent(ArrayList<String> fileContent, ArrayList<Distances> distanceMatrix) {
        for (String line : fileContent) {
            Matcher matcher = Pattern.compile("(\\w+) to (\\w+) = (\\d+)").matcher(line);
            if (matcher.find()) {
                distanceMatrix.add(new Distances(matcher.group(1), matcher.group(2), Integer.parseInt(matcher.group(3))));
            }
        }
    }

    static int calculateDistances(ArrayList<Distances> distanceMatrix, boolean minSearch) {
        int result;
        if (minSearch){
            result= Integer.MAX_VALUE;
        }else{
            result= Integer.MIN_VALUE;
        }
        ArrayList<String> citiesPermutationList = new ArrayList<>();
        int n = createCitiesSet(distanceMatrix).size();
        String[] arr = new String[n];
        String[] cityArray = createCitiesSet(distanceMatrix).toArray(arr);
        permute(Arrays.asList(cityArray), 0, citiesPermutationList);

        for (String string : citiesPermutationList) {
            String tmp = string.replace("[", "").replace("]", "").replace(" ", "");
            if (minSearch && calculateRoute(tmp.split(","), distanceMatrix) < result) {
                result = calculateRoute(tmp.split(","), distanceMatrix);
            }
            if (!minSearch && calculateRoute(tmp.split(","), distanceMatrix) > result) {
                result = calculateRoute(tmp.split(","), distanceMatrix);
            }
        }
        return result;
    }

    static int calculateRoute(String[] a, ArrayList<Distances> distanceMatrix) {
        int sum = 0;
        for (int i = 1; i < a.length; i++) {
            String startCity = a[i - 1];
            String endCity = a[i];
            try {
                sum += distanceMatrix.stream().filter(start -> start.start().equals(startCity)).filter(dest -> dest.end().equals(endCity)).map(Distances::distance).toList().get(0);
            } catch (ArrayIndexOutOfBoundsException e) {
                sum += distanceMatrix.stream().filter(start -> start.start().equals(endCity)).filter(dest -> dest.end().equals(startCity)).map(Distances::distance).toList().get(0);
            }
        }
        return sum;
    }

    static void permute(java.util.List<String> arr, int k, ArrayList<String> citiesPermutationList) {
        for (int i = k; i < arr.size(); i++) {
            java.util.Collections.swap(arr, i, k);
            permute(arr, k + 1, citiesPermutationList);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() - 1) {
            citiesPermutationList.add(java.util.Arrays.toString(arr.toArray()));
        }
    }

    static Set<String> createCitiesSet(ArrayList<Distances> distanceMatrix) {
        Set<String> citiesSet = new HashSet<>();
        for (Distances distances : distanceMatrix) {
            citiesSet.add(distances.start());
            citiesSet.add(distances.end());
        }
        return citiesSet;
    }

    record Distances(String start, String end, int distance) {
    }
}
