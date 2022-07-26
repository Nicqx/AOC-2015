package Days;

import utility.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day16 {
    ArrayList<String> fileContent = new FileReader("resources/D16/input").fileReaderArrayList();
    Map<Integer, Map<String, Integer>> knowledge = new HashMap<>();
    Map<String, Integer> clues = new HashMap<>();

    public Day16() {
        createClues(clues);
        processFileContent(knowledge, fileContent);
        System.out.println("D16 - This numbered Aunt Sue gave the present: " + exactMach(knowledge, clues));
        System.out.println("D16/2 - This numbered Aunt Sue gave the present: " + rangeMach(knowledge, clues));
    }

    static void processFileContent(Map<Integer, Map<String, Integer>> knowledge, ArrayList<String> fileContent) {
        for (String line : fileContent) {
            Matcher matcher = Pattern.compile("(\\d+): (\\w+): (\\d+), (\\w++): (\\d+), (\\w+): (\\d+)").matcher(line);
            if (matcher.find()) {
                Map<String, Integer> values = new HashMap<>();
                values.put(matcher.group(2), Integer.parseInt(matcher.group(3)));
                values.put(matcher.group(4), Integer.parseInt(matcher.group(5)));
                values.put(matcher.group(6), Integer.parseInt(matcher.group(7)));
                knowledge.put(Integer.parseInt(matcher.group(1)), values);
            }
        }
    }

    static Object exactMach(Map<Integer, Map<String, Integer>> knowledge, Map<String, Integer> clues) {
        Map<Integer, Map<String, Integer>> knowledgeCopy = new HashMap<>();
        for (int element : knowledge.keySet()) {
            knowledgeCopy.put(element, knowledge.get(element));
        }
        for (String thing : clues.keySet()) {
            ArrayList<Integer> removableList = new ArrayList<>();
            for (int name : knowledgeCopy.keySet()) {
                if (knowledgeCopy.get(name).containsKey(thing) && !Objects.equals(knowledgeCopy.get(name).get(thing), clues.get(thing))) {
                    removableList.add(name);
                }
            }
            for (int candidate : removableList) {
                knowledgeCopy.remove(candidate);
            }
        }
        return knowledgeCopy.keySet().toArray()[0];
    }

    static Object rangeMach(Map<Integer, Map<String, Integer>> knowledge, Map<String, Integer> clues) {
        Map<Integer, Map<String, Integer>> knowledgeCopy = new HashMap<>();
        for (int element : knowledge.keySet()) {
            knowledgeCopy.put(element, knowledge.get(element));
        }
        for (String thing : clues.keySet()) {
            ArrayList<Integer> removableList = new ArrayList<>();
            for (int name : knowledgeCopy.keySet()) {
                switch (thing) {
                    case "cats":
                    case "trees":
                        if (knowledgeCopy.get(name).containsKey(thing) && knowledgeCopy.get(name).get(thing) < clues.get(thing)) {
                            removableList.add(name);
                        }
                        break;
                    case "pomeranians":
                    case "goldfish":
                        if (knowledgeCopy.get(name).containsKey(thing) && knowledgeCopy.get(name).get(thing) > clues.get(thing)) {
                            removableList.add(name);
                        }
                        break;
                    default:
                        if (knowledgeCopy.get(name).containsKey(thing) && !Objects.equals(knowledgeCopy.get(name).get(thing), clues.get(thing))) {
                            removableList.add(name);
                        }
                }
            }
            for (int candidate : removableList) {
                knowledgeCopy.remove(candidate);
            }
        }
        return knowledgeCopy.keySet().toArray()[1];
    }

    static void createClues(Map<String, Integer> clues) {
        clues.put("children", 3);
        clues.put("cats", 7);
        clues.put("samoyeds", 2);
        clues.put("pomeranians", 3);
        clues.put("akitas", 0);
        clues.put("vizslas", 0);
        clues.put("goldfish", 5);
        clues.put("trees", 3);
        clues.put("cars", 2);
        clues.put("perfumes", 1);
    }
}
