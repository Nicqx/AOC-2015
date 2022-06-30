package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13 {
    ArrayList<Happiness> happinessMatrix = new ArrayList<>();
    Set<String> namesSet = new HashSet<>();
    ArrayList<String> namesPermutationList = new ArrayList<>();

    public Day13() {
        try (Scanner scanner = new Scanner(new File("resources/D13/input"))) {
            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                Matcher matcher = Pattern.compile("(\\w+) would gain (\\d+) happiness units by sitting next to (\\w+).").matcher(input);
                if (matcher.find()) {
                    happinessMatrix.add(new Happiness(matcher.group(1), matcher.group(3), Integer.parseInt(matcher.group(2))));
                }
                matcher = Pattern.compile("(\\w+) would lose (\\d+) happiness units by sitting next to (\\w+).").matcher(input);
                if (matcher.find()) {
                    happinessMatrix.add(new Happiness(matcher.group(1), matcher.group(3), Integer.parseInt(matcher.group(2)) * -1));
                }
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        createPersonsSet();
        int n = namesSet.size();
        String[] arr = new String[n];
        String[] nameArray = namesSet.toArray(arr);
        permute(Arrays.asList(nameArray), 0);
        int maxHappiness = 0;
        for (String string : namesPermutationList) {
            String tmp = string.replace("[", "").replace("]", "").replace(" ", "");
            tmp = tmp + "," + tmp.split(",")[0];
            if (calculateHappiness(tmp.split(",")) > maxHappiness) {
                maxHappiness = calculateHappiness(tmp.split(","));
            }
        }
        System.out.println("D13 - the happiest value is: " + maxHappiness);
        namesPermutationList.clear();
        addMyself();
        createPersonsSet();
        n = namesSet.size();
        arr = new String[n];
        nameArray = namesSet.toArray(arr);
        permute(Arrays.asList(nameArray), 0);
        maxHappiness = 0;
        for (String string : namesPermutationList) {
            String tmp = string.replace("[", "").replace("]", "").replace(" ", "");
            tmp = tmp + "," + tmp.split(",")[0];
            if (calculateHappiness(tmp.split(",")) > maxHappiness) {
                maxHappiness = calculateHappiness(tmp.split(","));
            }
        }
        System.out.println("D13/2 - the happiest value with me is: " + maxHappiness);


    }

    private void addMyself() {
        for (String name : namesSet) {
            happinessMatrix.add(new Happiness("Me", name, 0));
            happinessMatrix.add(new Happiness(name, "Me", 0));
        }
    }

    private int calculateHappiness(String[] a) {
        int sum = 0;
        for (int i = 1; i < a.length; i++) {
            String firstPerson = a[i - 1];
            String secondPerson = a[i];

            sum += happinessMatrix.stream().filter(start -> start.person1().equals(firstPerson)).filter(dest -> dest.person2().equals(secondPerson)).map(Happiness::value).toList().get(0);
            sum += happinessMatrix.stream().filter(start -> start.person1().equals(secondPerson)).filter(dest -> dest.person2().equals(firstPerson)).map(Happiness::value).toList().get(0);

        }
        return sum;
    }

    private void createPersonsSet() {
        for (Happiness distances : happinessMatrix) {
            namesSet.add(distances.person1());
            namesSet.add(distances.person2());
        }
    }

    private void permute(java.util.List<String> arr, int k) {
        for (int i = k; i < arr.size(); i++) {
            java.util.Collections.swap(arr, i, k);
            permute(arr, k + 1);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() - 1) {
            namesPermutationList.add(java.util.Arrays.toString(arr.toArray()));
        }
    }

    private record Happiness(String person1, String person2, int value) {
    }
}