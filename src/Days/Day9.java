package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9 {
    ArrayList<Distances> distanceMatrix = new ArrayList<>();
    Set<String> citiesSet = new HashSet<>();
    static ArrayList<String> citiesPermutationList = new ArrayList<>();

    public Day9() {
        try (Scanner scanner = new Scanner(new File("resources/D9/input"))) {
            while (scanner.hasNext()) {
                Matcher matcher = Pattern.compile("(\\w+) to (\\w+) = (\\d+)").matcher(scanner.nextLine());
                if (matcher.find()) {
                    distanceMatrix.add(new Distances(matcher.group(1), matcher.group(2), Integer.parseInt(matcher.group(3))));
                }
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        createCitiesSet();
        int n = citiesSet.size();
        String[] arr = new String[n];
        String[] cityArray = citiesSet.toArray(arr);
        permute(Arrays.asList(cityArray), 0);
        int minDistance = Integer.MAX_VALUE;
        int maxDistance = 0;
        for (String string : citiesPermutationList) {
            String tmp = string.replace("[", "").replace("]", "").replace(" ", "");
            if (calculateRoute(tmp.split(",")) < minDistance) {
                minDistance = calculateRoute(tmp.split(","));
            }
            if (calculateRoute(tmp.split(",")) > maxDistance) {
                maxDistance = calculateRoute(tmp.split(","));
            }
        }
        System.out.println("D9 - the shortest distance is: " + minDistance);
        System.out.println("D9/2 - the longest distance is: " + maxDistance);


    }

    private int calculateRoute(String[] a) {
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

    static void permute(java.util.List<String> arr, int k) {
        for (int i = k; i < arr.size(); i++) {
            java.util.Collections.swap(arr, i, k);
            permute(arr, k + 1);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() - 1) {
            citiesPermutationList.add(java.util.Arrays.toString(arr.toArray()));
        }
    }

    private void createCitiesSet() {
        for (Distances distances : distanceMatrix) {
            citiesSet.add(distances.start());
            citiesSet.add(distances.end());
        }
    }

    private record Distances(String start, String end, int distance) {
    }
}
